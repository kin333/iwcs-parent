package com.wisdom.iwcs.service.task.wcsSimulator;

import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.common.utils.NetWorkUtil;
import com.wisdom.iwcs.common.utils.constant.RabbitMQConstants;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.MesBusinessException;
import com.wisdom.iwcs.common.utils.taskUtils.ConsumerThread;
import com.wisdom.iwcs.domain.task.SubTaskAction;
import com.wisdom.iwcs.domain.upstream.mes.MesRespHandlerInfo;
import com.wisdom.iwcs.domain.upstream.mes.MesRespHandlerResult;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.mapper.task.SubTaskActionMapper;
import com.wisdom.iwcs.service.task.conditions.response.IResponseHandler;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SCADA;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_MES;
import static com.wisdom.iwcs.common.utils.TaskConstants.actionStatus.*;
import static com.wisdom.iwcs.common.utils.TaskConstants.executeMode.NO_PROMISE_ARRIVE;
import static com.wisdom.iwcs.common.utils.TaskConstants.executeMode.PROMISE_ARRIVE;

/**
 * 节点活动的消费者队列线程
 * @author han
 */
@Service
public class NodeActionThreadService extends ConsumerThread {
    private static final Logger logger = LoggerFactory.getLogger(NodeActionThreadService.class);

    public NodeActionThreadService() {
        super(RabbitMQConstants.NODE_ACTION_QUEUE,
                RabbitMQConstants.ROUTEKEY_NODE_ACTION,
                consumerActionInfo -> {
                    String id = consumerActionInfo.getMessage();
                    logger.info("开始处理{}action", id);
                    //根据id获取消息
                    SubTaskActionMapper subTaskActionMapper = AppContext.getBean("subTaskActionMapper");
                    SubTaskAction subTaskAction = subTaskActionMapper.selectByPrimaryKey(Long.valueOf(id));
                    for (int i = 0; i < 7; i++) {
                        if (subTaskAction != null) {
                            break;
                        }
                        //如果RabbitMQ的速度快于Action数据的插入速度,会造成这里查不到,延时2秒后再次查询
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subTaskAction = subTaskActionMapper.selectByPrimaryKey(Long.valueOf(id));
                    }
                    logger.info("开始处理子任务{}的action请求,id为{}",subTaskAction.getSubTaskNum(), id);
                    //有前置请求的,如果前置请求不满足,且是必达的,则不执行此次请求
                    if (StringUtils.isNotBlank(subTaskAction.getPreActions())) {
                        SubTaskAction preSubTaskAction = subTaskActionMapper.selectByActionCode(subTaskAction.getPreActions(), subTaskAction.getSubTaskNum());
                        logger.info("{}的子任务号{},前置请求为{}",preSubTaskAction, subTaskAction.getSubTaskNum(), subTaskAction.getPreActions());
                        if (!SEND_SUCCESS.equals(preSubTaskAction.getActionStatus()) && PROMISE_ARRIVE.equals(preSubTaskAction.getExecuteMode())) {
                            SubTaskAction tmpSubTaskAction = new SubTaskAction();
                            tmpSubTaskAction.setId(subTaskAction.getId());
                            tmpSubTaskAction.setActionStatus(CREATE);
                            subTaskActionMapper.updateByPrimaryKeySelective(tmpSubTaskAction);
                            logger.info("{}的子任务号{},前置请求{}状态为{},不满足",preSubTaskAction, subTaskAction.getSubTaskNum(),
                                    subTaskAction.getPreActions(), preSubTaskAction.getActionStatus());
                            return;
                        }
                    }

                    SubTaskAction tmpTaskAction = new SubTaskAction();
                    tmpTaskAction.setId(subTaskAction.getId());
                    logger.info("开始发送子任务{}的action请求,id为{}",subTaskAction.getSubTaskNum(), id);

                    while (true) {
                        try {
                            //发送消息
                            String resultBody = "";

                            //发送信息
                            resultBody = NetWorkUtil.transferContinueTaskUTF8(subTaskAction.getContent(), subTaskAction.getUrl());
                            JSONObject obj = new JSONObject(resultBody);

                            //处理返回结果
                            MesRespHandlerResult mesRespHandlerResult = new MesRespHandlerResult();
                            if (StringUtils.isNotBlank(subTaskAction.getResponseHandler())) {
                                IResponseHandler responseHandler = AppContext.getBean(subTaskAction.getResponseHandler());
                                if (responseHandler == null) {
                                    throw new BusinessException("找不到指定的返回值处理器:" + subTaskAction.getResponseHandler());
                                }
                                MesRespHandlerInfo mesRespHandlerInfo = new MesRespHandlerInfo();
                                mesRespHandlerInfo.setSubTaskNum(subTaskAction.getSubTaskNum());
                                //调用返回结果处理器
                                mesRespHandlerResult = responseHandler.disposeResult(obj, mesRespHandlerInfo);
                            } else {
                                //MES 默认结果处理
                                if (SRC_MES.equals(subTaskAction.getApp())) {
                                    if (MesResult.NG.equals(obj.getString("code"))) {
                                        mesRespHandlerResult.setHandleResult(false);
                                    }
                                } else if (SCADA.equals(subTaskAction.getApp())) {
                                    if (MesResult.NG.equals(obj.getString("code"))) {
                                        mesRespHandlerResult.setHandleResult(false);
                                    }
                                }
                            }
                            if (mesRespHandlerResult.isHandleResult()) {
                                tmpTaskAction.setActionStatus(SEND_SUCCESS);
                                logger.info("子任务{}的节点消息{}发送成功", subTaskAction.getSubTaskNum(), subTaskAction.getActionCode());
                            } else {
                                tmpTaskAction.setActionStatus(RESULT_ERROR);
                                //记录错误信息
                                tmpTaskAction.setErrorResultMessage(mesRespHandlerResult.getMessage());
                            }
                            logger.info("MES返回的消息体为:" + resultBody);
                            tmpTaskAction.setResultBody(resultBody);
                            break;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            //如果发送失败,则判断是否必达,必达需要重新发送
                            tmpTaskAction.setActionStatus(SEND_ERROR);
                            tmpTaskAction.setResultBody(e.getMessage());
                            e.printStackTrace();
                            if (NO_PROMISE_ARRIVE.equals(subTaskAction.getExecuteMode())) {
                                break;
                            }
                            try {
                                //循环请求需要循环调用
                                Thread.sleep(1000 * 20);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    subTaskActionMapper.updateByPrimaryKeySelective(tmpTaskAction);
                });
    }
}
