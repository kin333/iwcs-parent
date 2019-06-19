package com.wisdom.iwcs.service.callHik.callHikImpl;

import com.google.common.base.Strings;
import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.ListUtils;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseWb;
import com.wisdom.iwcs.domain.base.BaseWbBizConfig;
import com.wisdom.iwcs.domain.hikSync.GetOutPodDTO;
import com.wisdom.iwcs.domain.hikSync.OutPodDataDTO;
import com.wisdom.iwcs.domain.hikSync.OutPodRequestDTO;
import com.wisdom.iwcs.domain.outstock.dto.OutstockCalPodParamDTO;
import com.wisdom.iwcs.domain.outstock.dto.OutstockCalPodResultDTO;
import com.wisdom.iwcs.domain.system.Dictionary;
import com.wisdom.iwcs.domain.task.AgvTaskDetailOutstockProcess;
import com.wisdom.iwcs.domain.task.AgvTaskOutstockStock;
import com.wisdom.iwcs.domain.task.WbAgvTask;
import com.wisdom.iwcs.domain.task.WbTaskDetail;
import com.wisdom.iwcs.mapper.base.BaseWbBizConfigMapper;
import com.wisdom.iwcs.mapper.base.BaseWbMapper;
import com.wisdom.iwcs.mapper.system.DictionaryMapper;
import com.wisdom.iwcs.mapper.task.AgvTaskDetailOutstockProcessMapper;
import com.wisdom.iwcs.mapper.task.AgvTaskOutstockStockMapper;
import com.wisdom.iwcs.mapper.task.WbAgvTaskMapper;
import com.wisdom.iwcs.mapper.task.WbTaskDetailMapper;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.callHik.IGetOutPodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.wisdom.iwcs.common.utils.DeleteFlagEnum.NOT_DELETED;
import static com.wisdom.iwcs.common.utils.DictionaryConstants.DictionaryNameConstants.HIK_PRE_PICK;
import static com.wisdom.iwcs.common.utils.DictionaryConstants.DictionaryTypeConstants.HIK_PARAM;
import static com.wisdom.iwcs.common.utils.DictionaryConstants.DictionaryTypeConstants.TASK_PRI;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.OUT_POD_CODE;
import static com.wisdom.iwcs.common.utils.ValidFlagEnum.VALID;
import static com.wisdom.iwcs.common.utils.outStockUtils.PreSnFlagConstants.NOT_PRE_SN;
import static com.wisdom.iwcs.common.utils.outStockUtils.PreSnFlagConstants.PRE_SN;
import static com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum.OUTSTOCK_TASK;
import static com.wisdom.iwcs.common.utils.taskUtils.AgvTaskConstants.AgvTaskStatusConstants.TASK_CREATED;

/**
 * 货架出库
 *
 * @Author: cecilia.yang
 * @Date: 2019/2/26 18:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GetOutPodService implements IGetOutPodService {
    private final Logger logger = LoggerFactory.getLogger(GetOutPodService.class);

    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Autowired
    private ICommonService ICommonService;
    @Autowired
    private BaseWbMapper baseWbMapper;
    @Autowired
    private BaseWbBizConfigMapper baseWbBizConfigMapper;
    @Autowired
    private WbAgvTaskMapper wbAgvTaskMapper;
    @Autowired
    private WbTaskDetailMapper wbTaskDetailMapper;
    @Autowired
    private com.wisdom.iwcs.service.callHik.ITransferHikHttpRequestService ITransferHikHttpRequestService;
    @Autowired
    private ApplicationProperties applicationProperties;
    @Autowired
    private AgvTaskOutstockStockMapper agvTaskOutstockStockMapper;
    @Autowired
    private AgvTaskDetailOutstockProcessMapper agvTaskDetailOutstockProcessMapper;

    /**
     * 外部调用
     * 1.检查点位信息是否有效以及是否相应的业务信息
     * 2.去重bincode
     * 3.开始锁货架
     * 4.创建点位agv任务（判断是否已经产生相应类型的agv任务，不同的操作类型不能创建任务）
     * 5.创建点位任务详情表（记录bincode的当前的状态）
     * 6.往海康发送数据，调用货架出库
     *
     * @param getOutPodDTO
     * @return
     */
    @Override
    public Result getOutPod(GetOutPodDTO getOutPodDTO) {

        Result result = new Result();
        BaseWb baseWb = baseWbMapper.selectByWbCodeAndValidFlagAndDeleteFlag(getOutPodDTO.getWbCode(), VALID.getStatus(), NOT_DELETED.getStatus());
        BaseWbBizConfig baseWbBizConfig = baseWbBizConfigMapper.selectByWbCodeAndBizType(getOutPodDTO.getWbCode(), getOutPodDTO.getTaskType());
        Preconditions.checkBusinessError(baseWb == null, "无效的点位编号");
        Preconditions.checkBusinessError(baseWbBizConfig == null, "点位编号未配置对应业务信息");
        Date currentTime = new Date();
        String hikUrl = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String reqTime = formatter.format(currentTime);
        OutPodRequestDTO outPodRequestDTO = new OutPodRequestDTO();
        outPodRequestDTO.setReqCode(String.valueOf(System.currentTimeMillis()));
        outPodRequestDTO.setReqTime(reqTime);
        outPodRequestDTO.setInterfaceName(OUT_POD_CODE);
        List<Dictionary> hikParam = dictionaryMapper.selectByDictType(HIK_PARAM);
        outPodRequestDTO.setClientCode(applicationProperties.getHikParam().getClientCode());
        outPodRequestDTO.setTokenCode(applicationProperties.getHikParam().getTokenCode());

        Dictionary prePickDict = hikParam.stream().filter(p -> p.getDictName().equals(HIK_PRE_PICK)).findAny().get();
        //根据bincode前六位去重
        List<String> distinctBincodes = ICommonService.distinctBinCodeByPodCode(getOutPodDTO.getBincodes());
        String taskPri = returnTaskPriByTaskType(getOutPodDTO.getTaskType());

        List<OutPodDataDTO> outPodDataDTOList = new ArrayList<>();

        String wbTaskNo = returnWbTaskNoAndCreateAgvTask(getOutPodDTO, baseWbBizConfig);
        boolean recordTaskProcess = getOutPodDTO.getTaskType().equals(OUTSTOCK_TASK.getType()) && applicationProperties.getOutstock().isNoticeOutstockProcess();
        if (recordTaskProcess) {
            outstockTaskSyncTaskProcess(getOutPodDTO.getTaskProcess(), wbTaskNo);
        }

        List<String> podCodes = distinctBincodes.stream().map(bincode -> {
            return bincode.substring(0, 6);
        }).distinct().collect(Collectors.toList());
        ICommonService.updatePodLockByPodCodes(podCodes, getOutPodDTO.getTaskType(), TASK_CREATED);

        List<WbTaskDetail> wbTaskDetails = new ArrayList<>();
        distinctBincodes.stream().forEach(bincode -> {
            OutPodDataDTO outPodDataDTO = new OutPodDataDTO();
            String taskCode = UUID.randomUUID().toString().replaceAll("-", "");
            outPodDataDTO.setTaskCode(taskCode);
            outPodDataDTO.setBinCode(bincode);
            outPodDataDTO.setPriority(taskPri);
            outPodDataDTO.setWbCode(baseWb.getBerCode());
            outPodDataDTO.setLiftStatus(baseWbBizConfig.getAgvLiftStatus());
            outPodDataDTO.setPrePick(prePickDict.getDictValue());
            outPodDataDTOList.add(outPodDataDTO);

            WbTaskDetail wbTaskDetail = new WbTaskDetail();
            wbTaskDetail.setWbTaskNo(wbTaskNo);
            wbTaskDetail.setPodCode(bincode.substring(0, 6));
            wbTaskDetail.setBinCode(bincode);
            wbTaskDetail.setTaskStatus(TASK_CREATED);
            wbTaskDetail.setTaskSeq(taskCode);
            wbTaskDetail.setAgvActionRotateType(baseWbBizConfig.getAgvActionRotateType());
            wbTaskDetail.setCreatedTime(new Date());
            wbTaskDetail.setTaskType(getOutPodDTO.getTaskType());
            wbTaskDetails.add(wbTaskDetail);
        });
        wbTaskDetailMapper.insertList(wbTaskDetails);
        outPodRequestDTO.setData(outPodDataDTOList);


        String body = ITransferHikHttpRequestService.transferHikGetOutPod(outPodRequestDTO);
        ICommonService.handleHikResponseAndThrowException(body);
        result.setReturnData(wbTaskNo);
        return result;
    }

    /**
     * 同步出库记录
     *
     * @param taskProcessList
     * @param wbTaskNo
     */
    private void outstockTaskSyncTaskProcess(List<OutstockCalPodResultDTO> taskProcessList, String wbTaskNo) {
        List<AgvTaskOutstockStock> stockProcess = new ArrayList<>();
        List<AgvTaskDetailOutstockProcess> outstockDetailProcess = new ArrayList<>();
        taskProcessList.stream().forEach(taskProcess -> {
            boolean havePreSn = taskProcess.isHavePreSn();
            boolean newOutstockStock = false;
            OutstockCalPodParamDTO taskStock = taskProcess.getRequestInfo();
            taskStock.setWbTaskNo(wbTaskNo);
            AgvTaskOutstockStock agvTaskOutstockStock = agvTaskOutstockStockMapper.selectByOutstockCalPodParam(taskStock);
            if (agvTaskOutstockStock == null) {
                agvTaskOutstockStock = new AgvTaskOutstockStock();
                newOutstockStock = true;
                agvTaskOutstockStock.setTaskNo(wbTaskNo);
                agvTaskOutstockStock.setCargoOwner(taskStock.getCargoOwner());
                agvTaskOutstockStock.setMatCode(taskStock.getMatCode());
                agvTaskOutstockStock.setStgAreaCode(taskStock.getStgAreaCode());
                agvTaskOutstockStock.setBatchNum(taskStock.getBatchNum());
                agvTaskOutstockStock.setStkCharacter1(taskStock.getStkCharacter1());
                agvTaskOutstockStock.setStkCharacter2(taskStock.getStkCharacter2());
                agvTaskOutstockStock.setStkCharacter3(taskStock.getStkCharacter3());
                agvTaskOutstockStock.setStkCharacter4(taskStock.getStkCharacter4());
                agvTaskOutstockStock.setStkCharacter5(taskStock.getStkCharacter5());
                agvTaskOutstockStock.setTotalNeedQty(taskStock.getOutQty());
                agvTaskOutstockStock.setAlreadyCallQty(taskProcess.getTotalCalculateQty());
                agvTaskOutstockStock.setMissingQty(taskProcess.getMissingQty());
                agvTaskOutstockStock.setPreSnFlag(NOT_PRE_SN);
                if (havePreSn) {
                    agvTaskOutstockStock.setPreSnFlag(PRE_SN);
                    String callSns = ListUtils.convertListToString(taskProcess.getTotalCalculateSns());
                    String missingSns = ListUtils.convertListToString(taskProcess.getMissingSns());
                    agvTaskOutstockStock.setAlreadyCallSns(callSns);
                    agvTaskOutstockStock.setMissingSns(missingSns);
                }
                agvTaskOutstockStock.setCreatedTime(new Date());
                agvTaskOutstockStock.setLastModifiedTime(new Date());
                stockProcess.add(agvTaskOutstockStock);
            } else {
                BigDecimal totalCallQty = agvTaskOutstockStock.getAlreadyCallQty().add(taskProcess.getTotalCalculateQty());
                BigDecimal totalMissingQty = agvTaskOutstockStock.getMissingQty().subtract(taskProcess.getTotalCalculateQty());
                agvTaskOutstockStock.setAlreadyCallQty(totalCallQty);
                agvTaskOutstockStock.setMissingQty(totalMissingQty);
                if (havePreSn && !Strings.isNullOrEmpty(agvTaskOutstockStock.getAlreadyCallSns())) {
                    List<String> alreadyCallSns = ListUtils.convertStringToList(agvTaskOutstockStock.getAlreadyCallSns());
                    alreadyCallSns.addAll(taskProcess.getTotalCalculateSns());
                    String totalCallSns = ListUtils.convertListToString(alreadyCallSns);
                    agvTaskOutstockStock.setAlreadyCallSns(totalCallSns);
                }
                if (havePreSn && !Strings.isNullOrEmpty(agvTaskOutstockStock.getMissingSns())) {
                    List<String> missingSns = ListUtils.convertStringToList(agvTaskOutstockStock.getMissingSns());
                    missingSns.removeAll(taskProcess.getTotalCalculateSns());
                    String remainMissingSns = ListUtils.convertListToString(missingSns);
                    agvTaskOutstockStock.setMissingSns(remainMissingSns);
                }
                agvTaskOutstockStockMapper.updateByPrimaryKeySelective(agvTaskOutstockStock);
            }
            taskProcess.getData().stream().forEach(taskBincodeInfo -> {
                AgvTaskDetailOutstockProcess agvTaskDetailOutstockProcess = new AgvTaskDetailOutstockProcess();
                agvTaskDetailOutstockProcess.setTaskNo(wbTaskNo);
                agvTaskDetailOutstockProcess.setBinCode(taskBincodeInfo.getBincode());
                agvTaskDetailOutstockProcess.setPodCode(taskBincodeInfo.getBincode().substring(0, 6));
                agvTaskDetailOutstockProcess.setStkCode(taskBincodeInfo.getStkCode());
                agvTaskDetailOutstockProcess.setCalOutstockQty(taskBincodeInfo.getOutstockQty());
                agvTaskDetailOutstockProcess.setActualOutstockQty(new BigDecimal(0));
                agvTaskDetailOutstockProcess.setCreatedTime(new Date());
                agvTaskDetailOutstockProcess.setLastModifiedTime(new Date());
                agvTaskDetailOutstockProcess.setPreSnFlag(NOT_PRE_SN);
                if (havePreSn) {
                    agvTaskDetailOutstockProcess.setPreSnFlag(PRE_SN);
                    String outstockSns = ListUtils.convertListToString(taskBincodeInfo.getOutSns());
                    agvTaskDetailOutstockProcess.setCalOutstockSns(outstockSns);
                }
                outstockDetailProcess.add(agvTaskDetailOutstockProcess);
            });
        });
        if (stockProcess.size() != 0) {
            agvTaskOutstockStockMapper.insertList(stockProcess);
        }
        agvTaskDetailOutstockProcessMapper.insertList(outstockDetailProcess);
    }

    /**
     * 获取主任务编号
     *
     * @param getOutPodDTO
     * @param baseWbBizConfig
     * @return
     */
    private String returnWbTaskNoAndCreateAgvTask(GetOutPodDTO getOutPodDTO, BaseWbBizConfig baseWbBizConfig) {
        WbAgvTask existAgvTask = wbAgvTaskMapper.selectUnCompletedTaskByWbCode(getOutPodDTO.getWbCode());

        if (existAgvTask == null) {
            existAgvTask = new WbAgvTask();
            String taskNo = UUID.randomUUID().toString().replaceAll("-", "");
            existAgvTask.setTaskNo(taskNo);
            existAgvTask.setWbCode(getOutPodDTO.getWbCode());
            existAgvTask.setTaskType(getOutPodDTO.getTaskType());
            existAgvTask.setTaskStatus(TASK_CREATED);
            existAgvTask.setAgvActionRotateType(baseWbBizConfig.getAgvActionRotateType());
            existAgvTask.setAgvLiftStatus(baseWbBizConfig.getAgvLiftStatus());
            existAgvTask.setAgvReturnPodType(baseWbBizConfig.getAgvReturnPodType());
            existAgvTask.setBizItem1(getOutPodDTO.getBizItem1());
            existAgvTask.setBizItem2(getOutPodDTO.getBizItem2());
            existAgvTask.setBizItem3(getOutPodDTO.getBizItem3());
            existAgvTask.setBizItem4(getOutPodDTO.getBizItem4());
            existAgvTask.setBizItem5(getOutPodDTO.getBizItem5());
            existAgvTask.setSrcClientType(getOutPodDTO.getSrcClientType());
            existAgvTask.setSrcReqCode(getOutPodDTO.getSrcReqCode());
            existAgvTask.setSrcClientCode(getOutPodDTO.getSrcClientCode());
            existAgvTask.setSrcUserCode(getOutPodDTO.getSrcUserCode());
            existAgvTask.setBizOrderCode(getOutPodDTO.getBizOrderCode());
            existAgvTask.setCreatedTime(new Date());
            existAgvTask.setCycletp(getOutPodDTO.getLooplb());
            wbAgvTaskMapper.insertSelective(existAgvTask);
        }
        boolean isNotSameTaskType = !existAgvTask.getTaskType().equals(getOutPodDTO.getTaskType());
        Preconditions.checkBusinessError(isNotSameTaskType, "呼叫的任务类型与已存在的任务类型不同，不允许在同一点位呼叫不同任务");
        return existAgvTask.getTaskNo();
    }


    /**
     * 根据任务类型返回任务优先级
     *
     * @param taskType
     * @return
     */
    private String returnTaskPriByTaskType(String taskType) {
        String taskPri = "";
        List<Dictionary> hikParam = dictionaryMapper.selectByDictType(TASK_PRI);
        List<String> pri = hikParam.stream().filter(p -> p.getDictName().equals(taskType)).map(Dictionary::getDictValue).collect(Collectors.toList());
        if (pri.size() != 0) {
            taskPri = pri.get(0);
        }

        return taskPri;
    }


}
