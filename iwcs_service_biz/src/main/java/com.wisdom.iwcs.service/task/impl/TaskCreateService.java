package com.wisdom.iwcs.service.task.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.common.utils.constant.CondtionTriger;
import com.wisdom.iwcs.common.utils.exception.MesBusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.common.utils.taskUtils.TaskPriorityEnum;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.task.*;
import com.wisdom.iwcs.domain.task.dto.PublicContextDTO;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.base.BaseWhAreaMapper;
import com.wisdom.iwcs.mapper.elevator.EleControlTaskMapper;
import com.wisdom.iwcs.mapper.elevator.ElevatorMapper;
import com.wisdom.iwcs.mapper.task.*;
import com.wisdom.iwcs.mapstruct.task.TaskContextMapStruct;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.task.conditions.strategy.IStrategyHandler;
import com.wisdom.iwcs.service.task.intf.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.wisdom.iwcs.common.utils.TaskConstants.handlerName.ACTIONCHECKHANDLER;
import static com.wisdom.iwcs.common.utils.TaskConstants.mainTaskStatus.MAIN_NOT_ISSUED;
import static com.wisdom.iwcs.common.utils.YZConstants.LOCK;

/**
 * 任务创建
 * @Author george
 * @Date 2019/7/3 9:14
 */
@Service
public class TaskCreateService implements ITaskCreateService {
    private final Logger logger = LoggerFactory.getLogger(TaskCreateService.class);

    @Autowired
    private MainTaskMapper mainTaskMapper;
    @Autowired
    MesRequestService mesRequestService;
    @Autowired
    TaskRelActionMapper taskRelActionMapper;
    @Autowired
    MapResouceService mapResouceService;
    @Autowired
    private SubTaskConditionMapper subTaskConditionMapper;
    @Autowired
    private TaskRelConditionMapper taskRelConditionMapper;
    @Autowired
    private TaskContextMapper taskContextMapper;
    @Autowired
    private MainTaskResPreHandlerMapper mainTaskResPreHandlerMapper;
    @Autowired
    private MainTaskTypeMapper mainTaskTypeMapper;
    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private BasePodDetailMapper basePodDetailMapper;
    @Autowired
    private TaskRelMapper taskRelMapper;
    @Autowired
    ResourceHandlerStrategyMapper resourceHandlerStrategyMapper;

    /**
     * 添加子任务条件
     * @param
     * @return
     */
    public void addSubTaskCondition(String templCode, String subTaskNum){
        logger.info("{}开始生成前后置条件", subTaskNum);
        //通过主任务编号和子任务编号查询
        List<TaskRelCondition> taskRelConditionList = taskRelConditionMapper.selectByTemplCode(templCode);
        for (TaskRelCondition taskRelCondition: taskRelConditionList){
            SubTaskCondition subTaskCondition = new SubTaskCondition();
            subTaskCondition.setSubTaskNum(subTaskNum);
            subTaskCondition.setSubscribeEvent(taskRelCondition.getSubscribeEvent());
            subTaskCondition.setConditonTriger(taskRelCondition.getConditonTriger());
            subTaskCondition.setConditonHandler(taskRelCondition.getConditonHandler());
            subTaskCondition.setCreateDate(new Date());
            subTaskCondition.setStrategyCode(taskRelCondition.getStrategyCode());
            logger.info("{}子任务的策略号为:{} 处理器为:{}", subTaskNum, taskRelCondition.getStrategyCode(), taskRelCondition.getConditonHandler());
            subTaskConditionMapper.insertSelective(subTaskCondition);
        }

        //添加活动检查后置条件
        List<TaskRelAction> actions = taskRelActionMapper.selectExecuteModeByTempCode(templCode);
        if(actions.size() > 0) {
            SubTaskCondition subTaskCondition = new SubTaskCondition();
            subTaskCondition.setSubTaskNum(subTaskNum);
            subTaskCondition.setConditonTriger(CondtionTriger.POST_CONDITION.getCode());
            subTaskCondition.setConditonHandler(ACTIONCHECKHANDLER);
            subTaskCondition.setCreateDate(new Date());
            subTaskConditionMapper.insertSelective(subTaskCondition);
        }
    }

    /**
     * 添加主任务
     * @param
     * @return
     */
    @Override
    public String mainTaskCommonAdd(String taskTypeCode, String areaCode, Integer priority){
        String mainTaskNum = "";
        //创建主任务
        MainTask mainTaskCreate = new MainTask();
        mainTaskNum = CodeBuilder.codeBuilder("M");
        mainTaskCreate.setMainTaskNum(mainTaskNum);
        mainTaskCreate.setCreateDate(new Date());
        mainTaskCreate.setPriority(priority);
        mainTaskCreate.setMainTaskTypeCode(taskTypeCode);
        mainTaskCreate.setAreaCode(areaCode);
        mainTaskCreate.setTaskStatus(MAIN_NOT_ISSUED);
        mainTaskMapper.insertSelective(mainTaskCreate);

        return mainTaskNum;
    }


    /**
     * 通用主任务
     * @param taskType
     * @param
     * @param taskPri
     * @return
     */
    public String createMainTasks(String taskType,String taskPri,String jsonString){
        String mainTaskNum = "";
        MainTask mainTaskCreate = new MainTask();
        mainTaskNum = CodeBuilder.codeBuilder("M");
        mainTaskCreate.setMainTaskNum(mainTaskNum);
        mainTaskCreate.setCreateDate(new Date());
        if (StringUtils.isNotEmpty(taskPri)){
            mainTaskCreate.setPriority(TaskPriorityEnum.getPriorityByCode(taskPri));
        }
        mainTaskCreate.setMainTaskTypeCode(taskType);
        // mainTaskCreate.setAreaCode(areaCode);
        if (StringUtils.isNotEmpty(jsonString)){
            mainTaskCreate.setStaticViaPaths(jsonString);
        }
        mainTaskCreate.setTaskStatus(MAIN_NOT_ISSUED);
        mainTaskMapper.insertSelective(mainTaskCreate);
        return mainTaskNum;
    }

    /**
     * 通用的创建主任务接口
     * @param createRequest
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public MesResult publicTaskCreate(CreateMainTaskRequest createRequest) {
        //1.参数校验
        logger.info("开始校验创建主任务{}参数", createRequest.getTaskCode());
        MainTask mainTask = checkCreateMainParam(createRequest);

        //2.资源预处理
        logger.info("开始预处理主任务{}参数", createRequest.getTaskCode());
        preHandlerParams(createRequest);


        //3.创建主任务
        mainTask.setCreateDate(new Date());
        mainTask.setTaskStatus(MAIN_NOT_ISSUED);
        mainTaskMapper.insertSelective(mainTask);

        //4.创建子任务共享数据区域--任务上下文
        TaskContext taskContext = new TaskContext();
        taskContext.setMainTaskNum(mainTask.getMainTaskNum());
        taskContext.setCreateTime(new Date());
        PublicContextDTO context = createRequest.getContext();
        if (context != null) {
            taskContext.setContext(JSONObject.toJSONString(context));
        }
        taskContextMapper.insertSelective(taskContext);


        return new MesResult();
    }

    /**
     * 预处理创建主任务参数
     * @param createRequest
     */
    private void preHandlerParams(CreateMainTaskRequest createRequest) {
        String reqCode = createRequest.getReqCode();
        //查询预处理信息
        List<MainTaskResPreHandler> mainTaskResPreHandlers
                = mainTaskResPreHandlerMapper.selectByMainTaskType(createRequest.getMainTaskType());
        if (mainTaskResPreHandlers == null || mainTaskResPreHandlers.size() <= 0) {
            return;
        }
        for (MainTaskResPreHandler mainTaskResPreHandler : mainTaskResPreHandlers) {
            Preconditions.checkMesBusinessError(StringUtils.isEmpty(mainTaskResPreHandler.getResourceLocateType()),
                    "资源定位方法不能为空", reqCode);
            //通过反射,根据资源定位方法和资源定位参数,返回需要的资源值
            Class<? extends CreateMainTaskRequest> aClass = createRequest.getClass();
            //最终的资源值
            Object resource = null;
            if (StringUtils.isNotEmpty(mainTaskResPreHandler.getResourceLocateParams())) {
                ResourceLocateParams resourceLocateParams
                        = JSONObject.parseObject(mainTaskResPreHandler.getResourceLocateParams(), ResourceLocateParams.class);
                try {
                    Method method = aClass.getMethod(mainTaskResPreHandler.getResourceLocateType(), ResourceLocateParams.class);
                    resource = method.invoke(createRequest, resourceLocateParams);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

            } else {
                try {
                    Method method = aClass.getMethod(mainTaskResPreHandler.getResourceLocateType());
                    resource = method.invoke(createRequest);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            //执行校验策略
            executeHandler(mainTaskResPreHandler.getValidationStrategy(), resource);

            //执行操作策略
            executeHandler(mainTaskResPreHandler.getOperationStrategy(), resource);
        }
    }

    /**
     * 执行操作或者校验策略
     * @param allStrategy
     */
    private void executeHandler(String allStrategy, Object resource) {
        if(StringUtils.isNotEmpty(allStrategy)) {
            String[] strategys = allStrategy.split(";");
            for (String strategy : strategys) {
                ResourceHandlerStrategy resourceHandlerStrategie = resourceHandlerStrategyMapper.selectByStrategyCode(strategy);
                //获取策略处理器
                IStrategyHandler strategyHandler = AppContext.getBean(resourceHandlerStrategie.getStrategyHandler());
                Preconditions.checkMesBusinessError(strategyHandler == null, strategy + "策略不存在");
                if (StringUtils.isNotEmpty(resourceHandlerStrategie.getStrategyParams())) {
//                    BaseStrategyParams strategyParams = JSONObject.parseObject(resourceHandlerStrategie.getStrategyParams(), BaseStrategyParams.class);
                    strategyHandler.strategyHandler(resource, resourceHandlerStrategie.getStrategyParams());
                } else {
                    strategyHandler.strategyHandler(resource);
                }
            }

        }
    }

    /**
     * 创建主任务参数校验
     * @param createRequest
     * @return
     */
    private MainTask checkCreateMainParam(CreateMainTaskRequest createRequest) {
        String reqCode = createRequest.getReqCode();
        MainTask mainTask = new MainTask();
        //任务号
        String mainTaskNum;
        if (StringUtils.isNotEmpty(createRequest.getTaskCode())) {
            mainTaskNum = createRequest.getTaskCode();
            MainTask tmpMainTask = mainTaskMapper.selectByMainTaskNum(mainTaskNum);
            if (tmpMainTask != null) {
                throw new MesBusinessException(mainTaskNum + "任务号已存在");
            }
        } else {
            mainTaskNum = CodeBuilder.codeBuilder("M");
        }
        mainTask.setMainTaskNum(mainTaskNum);

        //任务类型
        String mainTaskType = createRequest.getMainTaskType();
        Preconditions.checkMesBusinessError(StringUtils.isEmpty(mainTaskType), "任务类型不能为空", reqCode);
        MainTaskType tmpMainTaskType = mainTaskTypeMapper.selectByMainTaskTypeCode(mainTaskType);
        Preconditions.checkMesBusinessError(tmpMainTaskType == null, mainTaskType + "任务类型不存在,请维护", reqCode);
        mainTask.setMainTaskTypeCode(mainTaskType);
        mainTask.setPriority(tmpMainTaskType.getPriority());

        //货架号
        int num = taskRelMapper.selectByMainTaskTypeAndStaticPod(mainTaskType);
        if (num > 0) {
            String podCode = createRequest.getPodCode();
            Preconditions.checkMesBusinessError(StringUtils.isEmpty(podCode), "货架号不能为空", reqCode);
            BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCode(podCode);
            Preconditions.checkMesBusinessError(basePodDetail == null, podCode + "货架未录入系统", reqCode);
//            Preconditions.checkMesBusinessError(LOCK.equals(basePodDetail.getInLock()), podCode + "货架已被锁定", reqCode);
            //锁定货架
//            mapResouceService.lockPod(basePodDetail);
            mainTask.setStaticPodCode(podCode);
        }

        //站点集合
        List<String> points = createRequest.getStaticViaPaths();
        List<String> berCodes = new ArrayList<>();
        List<TaskRel> taskRels = taskRelMapper.selectByMainTaskTypeAndStaticPoint(mainTaskType);
        if (taskRels != null && taskRels.size() > 0) {
            if (points == null || points.size() <= 0) {
                throw new MesBusinessException("站点集合不能为空");
            }
            int max = 0;
            for (TaskRel taskRel : taskRels) {
                String startPointAccessValue = taskRel.getStartPointAccessValue();
                if (StringUtils.isNotEmpty(startPointAccessValue)) {
                    Integer integer = Integer.valueOf(startPointAccessValue);
                    if (integer > max) {
                        max = integer;
                    }
                }
                String endPointAccessValue = taskRel.getEndPointAccessValue();
                if (StringUtils.isNotEmpty(endPointAccessValue)) {
                    Integer integer = Integer.valueOf(endPointAccessValue);
                    if (integer > max) {
                        max = integer;
                    }
                }
            }
            Preconditions.checkMesBusinessError(max != points.size(), "站点集合数量不匹配,应该填入的数量为:" + max);
            for (String point : points) {
                BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(point);
                Preconditions.checkMesBusinessError(baseMapBerth == null, point + "点位未录入系统");
                Preconditions.checkMesBusinessError(LOCK.equals(baseMapBerth.getInLock()), point + "点位已被锁定", reqCode);
                berCodes.add(baseMapBerth.getBerCode());
            }
            String jsonString = JSONArray.toJSONString(berCodes);
            mainTask.setStaticViaPaths(jsonString);
        }
        return mainTask;
    }
}
