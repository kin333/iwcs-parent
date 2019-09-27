package com.wisdom.iwcs.service.task.template;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.taskUtils.TaskContextUtils;
import com.wisdom.iwcs.domain.task.*;
import com.wisdom.iwcs.domain.task.dto.BaseContextInfo;
import com.wisdom.iwcs.domain.task.dto.ContextDTO;
import com.wisdom.iwcs.domain.task.dto.PublicContextDTO;
import com.wisdom.iwcs.domain.task.dto.TempdateRelatedContext;
import com.wisdom.iwcs.mapper.task.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 发送消息体注入信息类
 * @author han
 */
@Service
public class TemplateRelatedServer {
    private final Logger logger = LoggerFactory.getLogger(TemplateRelatedServer.class);

    private Pattern pattern = Pattern.compile(REGIX);
    /**
     * 正则表达式
     */
    private final static String REGIX = "\\$\\{[^\\}]*}";
    /**
     * 选填项
     */
    private final String OP = "OP";
    /**
     * 必填项
     */
    private final String NC = "NC";
    /**
     * 表示子任务类
     */
    private final String SUB_TASK = "SubTask";
    /**
     * 表示主任务类
     */
    private final String MAIN_TASK = "MainTask";
    /**
     * 表示上下文的一些信息
     */
    private final String TEMP_RELATED_CONTEXT = "TempdateRelatedContext";
    /**
     * 表示主任务的上下文信息
     */
    private final String CONTEXT = "Context";

    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    MainTaskMapper mainTaskMapper;
    @Autowired
    SubTaskTypMapper subTaskTypMapper;
    @Autowired
    TaskRelConditionMapper taskRelConditionMapper;
    @Autowired
    ApplicationProperties applicationProperties;
    @Autowired
    TaskContextMapper taskContextMapper;
    @Autowired
    TaskRelActionMapper taskRelActionMapper;

    /**
     * 根据子任务编号,把子任务的信息插入到子任务对应的发送消息体中
     * @param subTaskNum 子任务编号
     * @return 加入参数后的发送消息体
     */
    public String templateIntoInfo(String subTaskNum) {
        //1.查询子任务信息
        if (StringUtils.isEmpty(subTaskNum)) {
            throw new BusinessException("tempateIntoInfo()的参数不能为空");
        }
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskNum);
        checkNull(subTask, "子任务不存在:" + subTaskNum);

        //2.查询发送消息体
        if (StringUtils.isEmpty(subTask.getSubTaskTyp())) {
            throw new BusinessException("数据异常: 子任务无任务类型");
        }
        SubTaskTyp subTaskTyp = subTaskTypMapper.selectByTypeCode(subTask.getSubTaskTyp());
        checkNull(subTaskTyp, subTaskNum + "子任务类型不存在:" + subTask.getSubTaskTyp());
        String sendTemplate = subTaskTyp.getSubTaskMesSend();

        return baseTemplateIntoInfo(subTask, sendTemplate);
    }

    /**
     * 子任务活动模板插入
     * @return
     */
    public String actionTemplateInfo(SubTask subTask, String actionCode) {
        //查询发送消息体
        if (StringUtils.isEmpty(subTask.getTemplCode())) {
            throw new BusinessException("数据异常: 子任务无任务类型TemplCode");
        }
        TaskRelAction taskRelAction = taskRelActionMapper.selectByActionCode(actionCode);
        checkNull(taskRelAction, subTask.getSubTaskNum() + "任务活动类型不存在:" + subTask.getSubTaskTyp());
        String sendTemplate = taskRelAction.getContent();

        return baseTemplateIntoInfo(subTask, sendTemplate);
    }

    /**
     * 基础的模板插入工具类
     * @param subTask 要使用模板的子任务
     * @param sendTemplate 模板
     * @return
     */
    private String baseTemplateIntoInfo(SubTask subTask, String sendTemplate)  {
        String subTaskNum = subTask.getSubTaskNum();
        //3.查询子任务对应的主任务信息
        if (StringUtils.isEmpty(subTask.getMainTaskNum())) {
            throw new BusinessException("数据异常: 子任务无主任务编号");
        }
        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(subTask.getMainTaskNum());
        checkNull(mainTask, "子任务无对应主任务:" + subTaskNum);

        //查询基础信息
        TempdateRelatedContext tempdateRelatedContext = getRequestInfo();

        //查询任务上下文表的context信息
        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(subTask.getMainTaskNum());
        checkNull(mainTask, "无对应的上下文信息:" + subTask.getMainTaskNum());
        String context = taskContext.getContext();
        PublicContextDTO publicContextDTO = JSONObject.parseObject(context, PublicContextDTO.class);

        //4.向发送消息体中插入消息
        while(true) {
            String[] values = gainTemplateValue(sendTemplate);
            if (values == null) {
                break;
            }
            if (values.length != 3) {
                throw new BusinessException("子任务" + subTaskNum + "发送消息体格式错误");
            }
            Object param = null;
            Class paramType = null;
            //拼接get方法名
            String methodName = "get" + values[2].substring(0, 1).toUpperCase() + values[2].substring(1);
            try {
                if (SUB_TASK.equals(values[1])) {
                    //向模板中加入子任务信息
                    Method declaredMethod = SubTask.class.getDeclaredMethod(methodName);
                    param = declaredMethod.invoke(subTask);
                    Field declaredField = SubTask.class.getDeclaredField(values[2]);
                    paramType = declaredField.getType();
                } else if (MAIN_TASK.equals(values[1])) {
                    //向模板中加入主任务信息
                    Method declaredMethod = MainTask.class.getDeclaredMethod(methodName);
                    param = declaredMethod.invoke(mainTask);
                    Field declaredField = MainTask.class.getDeclaredField(values[2]);
                    paramType = declaredField.getType();
                } else if (TEMP_RELATED_CONTEXT.equals(values[1])) {
                    //向模板中加入请求要求数据
                    Method declaredMethod = TempdateRelatedContext.class.getDeclaredMethod(methodName);
                    param = declaredMethod.invoke(tempdateRelatedContext);
                    Field declaredField = TempdateRelatedContext.class.getDeclaredField(values[2]);
                    paramType = declaredField.getType();
                } else if (CONTEXT.equals(values[1])) {
                    //向模板中加入请求要求数据
                    Method declaredMethod = PublicContextDTO.class.getDeclaredMethod(methodName);
                    param = declaredMethod.invoke(publicContextDTO);
                    Field declaredField = PublicContextDTO.class.getDeclaredField(values[2]);
                    paramType = declaredField.getType();
                } else {
                    throw new BusinessException("子任务" + subTaskNum + "的任务消息体错误: 无法找到" + values[1] + "的对应类");
                }
                if (param == null && NC.equals(values[0])) {
                    throw new BusinessException(values[2] + "是必填项,不能为null");
                }
            } catch (NoSuchMethodException | NoSuchFieldException e) {
                e.printStackTrace();
                throw new BusinessException("子任务" + subTaskNum + "模板参数名与真实类字段没有对应");
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
                throw new BusinessException("子任务" + subTaskNum + "模板插入异常:" + e.getMessage());
            }
            if (param != null) {
                sendTemplate = replaceTemplateValue(sendTemplate, param);
            } else if (String.class.equals(paramType)) {
                sendTemplate = replaceTemplateValue(sendTemplate, "");
            } else if (Integer.class.equals(paramType)) {
                sendTemplate = replaceTemplateValue(sendTemplate, 0);
            } else {
                sendTemplate = replaceTemplateValue(sendTemplate, "");
            }
        }
        logger.info("子任务{}消息体已生成", subTaskNum);

        return sendTemplate;
    }

    /**
     * 获取${}里面的值,并且将里面的值根据 . 分开
     * @return 如果没有${},则返回空字符串,如果有,则返回一个数组
     */
    private String[] gainTemplateValue(String data) {
        Matcher matcher = pattern.matcher(data);
        if (matcher.find()) {
            String paramCode = matcher.group();
            String substring = paramCode.substring(2, paramCode.length() - 1);
            return substring.split("\\.");
        }
        return null;
    }

    /**
     * 替换${}里面的值
     * @param param 替换的值
     */
    private String replaceTemplateValue(String data, Object param) {
        if (data == null) {
            return null;
        }
        if (param instanceof String) {
            return data.replaceFirst(REGIX, param.toString());
        } else if (param instanceof Date) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return data.replaceFirst(REGIX, formatter.format((Date) param));
        } else {
            return data.replaceFirst(REGIX,  param.toString());
        }
    }

    private void checkNull(Object obj, String errorInfo) {
        if (obj == null) {
            throw new BusinessException("数据不存在:" + errorInfo);
        }
    }

    /**
     * 生成请求的一些必填信息
     * @return
     */
    public TempdateRelatedContext getRequestInfo() {
        TempdateRelatedContext tempdateRelatedContext = new TempdateRelatedContext();
        String reqCode = UUID.randomUUID().toString().replaceAll("-", "");
        if (reqCode.length() > 32) {
            reqCode = reqCode.substring(0, 32);
        }
        tempdateRelatedContext.setReqCode(reqCode);
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tempdateRelatedContext.setReqTime(timeFormat.format(new Date()));
        tempdateRelatedContext.setClientCode(applicationProperties.getHikParam().getClientCode());
        tempdateRelatedContext.setTokenCode(applicationProperties.getHikParam().getTokenCode());
        return tempdateRelatedContext;
    }

    /**
     * 获取子任务对应的所有的上下文信息
     * @param subTaskNum
     * @return
     */
    public BaseContextInfo getAllContext(String subTaskNum) {
        BaseContextInfo baseContextInfo = new BaseContextInfo();
        //1.查询子任务信息
        if (StringUtils.isEmpty(subTaskNum)) {
            throw new BusinessException("tempateIntoInfo()的参数不能为空");
        }
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskNum);
        checkNull(subTask, "子任务不存在:" + subTaskNum);
        baseContextInfo.setSubTask(subTask);

        //2.查询子任务对应的主任务信息
        if (StringUtils.isEmpty(subTask.getMainTaskNum())) {
            throw new BusinessException("数据异常: 子任务无主任务编号");
        }
        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(subTask.getMainTaskNum());
        checkNull(mainTask, "子任务无对应主任务:" + subTaskNum);
        baseContextInfo.setMainTask(mainTask);

        //3.查询基础信息
        TempdateRelatedContext tempdateRelatedContext = getRequestInfo();
        baseContextInfo.setTempdateRelatedContext(tempdateRelatedContext);

        //4.查询任务上下文表的context信息
        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(subTask.getMainTaskNum());
        checkNull(mainTask, "子任务无对应的上下文信息:" + subTaskNum);
        String context = taskContext.getContext();
        PublicContextDTO publicContextDTO = JSONObject.parseObject(context, PublicContextDTO.class);
        baseContextInfo.setPublicContextDTO(publicContextDTO);

        return baseContextInfo;
    }

    /**
     * 根据主任务号查询对应的上下文信息
     * @param mainTaskNum
     * @return
     */
    public BaseContextInfo getMainTaskContext(String mainTaskNum) {
        BaseContextInfo baseContextInfo = new BaseContextInfo();
        //1.查询子任务对应的主任务信息
        if (StringUtils.isEmpty(mainTaskNum)) {
            throw new BusinessException("数据异常: 子任务无主任务编号");
        }
        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(mainTaskNum);
        checkNull(mainTask, "无对应主任务:" + mainTaskNum);
        baseContextInfo.setMainTask(mainTask);

        //2.查询任务上下文表的context信息
        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(mainTaskNum);
        checkNull(mainTask, "无对应的上下文信息:" + mainTaskNum);
        String context = taskContext.getContext();
        PublicContextDTO publicContextDTO = JSONObject.parseObject(context, PublicContextDTO.class);
        baseContextInfo.setPublicContextDTO(publicContextDTO);

        return baseContextInfo;
    }

}
