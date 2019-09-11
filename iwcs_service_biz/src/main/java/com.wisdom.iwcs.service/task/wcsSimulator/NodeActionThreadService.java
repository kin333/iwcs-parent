package com.wisdom.iwcs.service.task.wcsSimulator;

import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.common.utils.NetWorkUtil;
import com.wisdom.iwcs.common.utils.constant.RabbitMQConstants;
import com.wisdom.iwcs.common.utils.taskUtils.ConsumerThread;
import com.wisdom.iwcs.domain.task.SubTaskAction;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.mapper.task.SubTaskActionMapper;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_MES;
import static com.wisdom.iwcs.common.utils.TaskConstants.actionStatus.*;
import static com.wisdom.iwcs.common.utils.TaskConstants.executeMode.NO_PROMISE_ARRIVE;

/**
 * 节点活动的消费者队列线程
 * @author han
 */
public class NodeActionThreadService extends ConsumerThread {
    private static final Logger logger = LoggerFactory.getLogger(NodeActionThreadService.class);

    public NodeActionThreadService() {
        super(RabbitMQConstants.NODE_ACTION_QUEUE,
                RabbitMQConstants.ROUTEKEY_NODE_ACTION,
                consumerActionInfo -> {
                    String id = consumerActionInfo.getMessage();
                    //根据id获取消息
                    SubTaskActionMapper subTaskActionMapper = AppContext.getBean("subTaskActionMapper");
                    SubTaskAction subTaskAction = subTaskActionMapper.selectByPrimaryKey(Long.valueOf(id));
                    if (!CREATE.equals(subTaskAction.getActionStatus())) {
                        return;
                    }
                    //有前置请求的,如果前置请求不满足,则不执行此次请求
                    if (StringUtils.isNotBlank(subTaskAction.getPreActions())) {
                        SubTaskAction preSubTaskAction = subTaskActionMapper.selectByActionCode(subTaskAction.getPreActions(), subTaskAction.getSubTaskNum());
                        if (!SEND_SUCCESS.equals(preSubTaskAction.getActionStatus())) {
                            return;
                        }
                    }

                    SubTaskAction tmpTaskAction = new SubTaskAction();
                    tmpTaskAction.setId(subTaskAction.getId());

                    try {
                        //发送消息
                        String resultBody = "";
                        while (true) {
                            resultBody = NetWorkUtil.transferContinueTask(subTaskAction.getContent(), subTaskAction.getUrl());
                            JSONObject obj = new JSONObject(resultBody);

                            if (SRC_MES.equals(subTaskAction.getApp())) {
                                //调用MES
                                if (obj.getString("code").equals(MesResult.OK)) {
                                    tmpTaskAction.setActionType(SEND_SUCCESS);
                                    logger.info("子任务{}的节点消息{}发送成功", subTaskAction.getSubTaskNum(), subTaskAction.getActionCode());
                                    break;
                                } else {
                                    tmpTaskAction.setActionType(SEND_ERROR);
                                    if (NO_PROMISE_ARRIVE.equals(subTaskAction.getExecuteMode())) {
                                        break;
                                    }
                                    //循环请求需要循环调用
                                    Thread.sleep(1000 * 30);
                                }
                            }
                        }
                        logger.info("MES返回的消息体为:" + resultBody);
                        tmpTaskAction.setResultBody(resultBody);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        tmpTaskAction.setActionType(SEND_ERROR);
                        tmpTaskAction.setResultBody(e.getMessage());
                        e.printStackTrace();
                    } finally {
                        subTaskActionMapper.updateByPrimaryKeySelective(tmpTaskAction);
                    }
                });
    }
}
