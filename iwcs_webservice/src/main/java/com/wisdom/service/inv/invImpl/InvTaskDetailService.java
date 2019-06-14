package com.wisdom.service.inv.invImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.inv.InvTaskCondDTOandResultDetailMapStruct;
import com.wisdom.controller.mapstruct.inv.InvTaskCondDetailMapStruct;
import com.wisdom.controller.mapstruct.inv.InvTaskResultDetailDTOOrStockDTOMapStruct;
import com.wisdom.controller.mapstruct.inv.InvTaskResultDetailMapStruct;
import com.wisdom.event.inv.InvTaskFinishedEvent;
import com.wisdom.event.inv.InvTaskFinishedEventInfos;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.dto.BaseWbBizConfigDTO;
import com.wisdom.iwcs.domain.hikSync.GetOutPodDTO;
import com.wisdom.iwcs.domain.hikSync.ReturnPodDTO;
import com.wisdom.iwcs.domain.inv.InvTaskResultDetail;
import com.wisdom.iwcs.domain.inv.dto.InvTaskBincodeProcessDTO;
import com.wisdom.iwcs.domain.inv.dto.InvTaskResultDetailDTO;
import com.wisdom.iwcs.domain.inv.dto.InvTaskStartDto;
import com.wisdom.iwcs.domain.stock.dto.StockDTO;
import com.wisdom.iwcs.mapper.inv.InvTaskResultDetailMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.base.ICommonService;
import com.wisdom.service.base.baseImpl.BasePodDetailService;
import com.wisdom.service.base.baseImpl.BaseWbBizConfigService;
import com.wisdom.service.callHik.IGetOutPodService;
import com.wisdom.service.callHik.callHikImpl.ReturnPodService;
import com.wisdom.service.inv.IinvTaskDetailService;
import com.wisdom.service.stock.impl.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class InvTaskDetailService implements IinvTaskDetailService {
    private final Logger logger = LoggerFactory.getLogger(InvTaskDetailService.class);

    private final InvTaskResultDetailMapper invTaskDetailMapper;

    private final InvTaskResultDetailMapStruct invTaskDetailMapStruct;
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    BasePodDetailService basePodDetailService;

    @Autowired
    ICommonService iCommonService;

    @Autowired
    StockService stockService;

    @Autowired
    InvTaskCondDTOandResultDetailMapStruct invTaskCondDTOandResultDetailMapStruct;

    @Autowired
    InvTaskResultDetailDTOOrStockDTOMapStruct invTaskResultDetailDTOOrStockDTOMapStruct;

    @Autowired
    InvTaskCondDetailMapStruct invTaskCondDetailMapStruct;

    @Autowired
    BaseWbBizConfigService baseWbBizConfigService;

    @Autowired
    IGetOutPodService IGetOutPodService;

    @Autowired
    InvTaskBincodeProcessService invTaskBincodeProcessService;

    @Autowired
    InvTaskCondDetailService invTaskCondDetailService;
    @Autowired
    ReturnPodService returnPodService;

    @Autowired
    public InvTaskDetailService(InvTaskResultDetailMapStruct invTaskDetailMapStruct, InvTaskResultDetailMapper invTaskDetailMapper) {
        this.invTaskDetailMapStruct = invTaskDetailMapStruct;
        this.invTaskDetailMapper = invTaskDetailMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link InvTaskResultDetailDTO }
     * @return int
     */
    @Override
    public int addInvTaskDetail(InvTaskResultDetail record) {

        Integer userId = SecurityUtils.getCurrentUserId();
        record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        record.setCreatedTime(new Date());
        record.setCreatedBy(userId);
        record.setLastModifiedBy(userId);
        record.setLastModifiedTime(new Date());

        int num = invTaskDetailMapper.insert(record);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据srcInvNo查询
     *
     * @param srcInvNo {@link String }
     */
    @Override
    public List<InvTaskResultDetail> selectBySrcInvNo(String srcInvNo) {

        List<InvTaskResultDetail> invTaskDetailList = invTaskDetailMapper.queryInvResultOfSrcInvNo(srcInvNo);
        Preconditions.checkNotNull(invTaskDetailList, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);
        return invTaskDetailList;
    }

    /**
     * 根据srcInvNo查询所有
     *
     * @param srcInvNo {@link String }
     */
    public List<InvTaskResultDetail> selectAllBySrcInvNo(String srcInvNo) {
        Preconditions.checkBusinessError(srcInvNo == null || srcInvNo.isEmpty(), "缺少上游盘点号");
        List<InvTaskResultDetail> invTaskDetailList = invTaskDetailMapper.selectAllBySrcInvNo(srcInvNo);
        return invTaskDetailList;
    }


    /**
     * 根据主键删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        int num = invTaskDetailMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData< InvTaskResultDetailDTO > }
     */
    @Override
    public GridReturnData<InvTaskResultDetailDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<InvTaskResultDetailDTO> mGridReturnData = new GridReturnData<>();
        List<GridFilterInfo> filterList = gridPageRequest.getFilterList();
        Map<String, Object> map = new HashMap<>(2);
        filterList.forEach(gridFilterInfo -> {
            if (gridFilterInfo.getFilterKey() != null && gridFilterInfo.getFilterValue() != null) {
                map.put(gridFilterInfo.getFilterKey(), gridFilterInfo.getFilterValue());
            }
        });
        map.put("searchKey", gridPageRequest.getSearchKey());
        // 对map中的参数的合法性进行校验

        String sortMyBatisByString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMyBatisByString);

        List<InvTaskResultDetail> list = invTaskDetailMapper.selectPage(map);

        PageInfo<InvTaskResultDetail> pageInfo = new PageInfo<>(list);
        PageInfo<InvTaskResultDetailDTO> pageInfoFinal = new PageInfo<>(invTaskDetailMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 对盘点结果进行更新 填写实际数量
     */
    @Override
    public String updateActualInvData(InvTaskResultDetailDTO invTaskResultDetailDTO) {
        Integer userId = SecurityUtils.getCurrentUserId();
        InvTaskResultDetail invTaskResultDetail = invTaskDetailMapStruct.toEntity(invTaskResultDetailDTO);
        invTaskResultDetail.setInvStatus(1);
        //得到历史库存
        StockDTO stockDTO = invTaskResultDetailDTOOrStockDTOMapStruct.toEntity(invTaskResultDetailDTO);
        List<StockDTO> stockDTOList = stockService.selectSelective(stockDTO);

        //相应数据写入盘点结果表中
        invTaskResultDetail.setSysQty(stockDTOList.get(0).getAvailableQty());
        int num = addInvTaskDetail(invTaskResultDetail);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        // 修改 srcinvno bincode 对应inv_task_bincode_process表中的状态  由0 -> 1
        InvTaskBincodeProcessDTO invTaskBincodeProcessDTO = new InvTaskBincodeProcessDTO();
        invTaskBincodeProcessDTO.setTaskBincode(invTaskResultDetailDTO.getBincode());
        invTaskBincodeProcessDTO.setSrcInvNo(invTaskResultDetailDTO.getSrcInvNo());
        invTaskBincodeProcessService.updateInvBincodeStatus(invTaskBincodeProcessDTO);


        //下发一个通知有一个bincode 已经盘完 异步监听
        InvTaskFinishedEventInfos invTaskFinishedEventInfos = new InvTaskFinishedEventInfos();
        invTaskFinishedEventInfos.setSrcInvNo(invTaskResultDetailDTO.getSrcInvNo());
        invTaskFinishedEventInfos.setWbCode(invTaskResultDetailDTO.getWbCode());
        InvTaskFinishedEvent oneInvTaskFinishedEvent = new InvTaskFinishedEvent(invTaskFinishedEventInfos);
        applicationContext.publishEvent(oneInvTaskFinishedEvent);

        //返回原点 getAgvNextAction 等于1时 为原地
        if (ObjectUtils.isEmpty(invTaskResultDetailDTO.getAgvNextAction())) {
            ReturnPodDTO returnPodDTO = new ReturnPodDTO();
            returnPodDTO.setBincode(invTaskResultDetailDTO.getBincode());
            returnPodDTO.setTaskType("inventory");
            returnPodDTO.setWbCode(invTaskResultDetailDTO.getWbCode());
            returnPodService.returnPodRequest(returnPodDTO);
        }
        String s = "原库存为" + stockDTOList.get(0) + "实际库存" + invTaskResultDetailDTO.getInvQty();

        return s;
    }

    /**
     * 工作台正式调动小车  (暂时淘汰)
     */
    @Override
    public Map<String, Object> actuallyStartInv(String srcInvNo, String wbCode) {
        //通过invId 查询 盘点结果表 过滤掉已经盘点,得到未盘点数据
        List<InvTaskResultDetail> invTaskDetailList = invTaskDetailMapper.queryInvResultOfSrcInvNo(srcInvNo);
        if (ObjectUtils.isEmpty(invTaskDetailList)) {
            throw new BusinessException("任务已完成盘点或不存在该任务");
        }
        List<String> binCodeList = invTaskDetailList.stream().map(InvTaskResultDetail::getBincode).collect(Collectors.toList());
        //调动 avg
        GetOutPodDTO getOutPodDTO = new GetOutPodDTO();
        getOutPodDTO.setBincodes(binCodeList);
        getOutPodDTO.setTaskType("inventory");
        getOutPodDTO.setWbCode(wbCode);
        IGetOutPodService.getOutPod(getOutPodDTO);
        Map<String, Object> invTaskDetailMap = new HashMap<>(2);
        invTaskDetailMap.put("invTaskDetailLists", invTaskDetailList);
        return invTaskDetailMap;
    }

    /**
     * 开始盘点任务
     */
    public Result startInvTask(InvTaskStartDto invTaskStartDto) {
        if (ObjectUtils.isEmpty(invTaskStartDto.getSrcInvNo())) {
            throw new BusinessException("请输入任务号");
        }
        //查询Inv任务的剩余Bincode
        List<String> binCodeList = invTaskBincodeProcessService.queryTheRemainingBincodeOfInvTask(invTaskStartDto.getSrcInvNo());
        if (ObjectUtils.isEmpty(invTaskStartDto.getInvTaskCount())) {
            BaseWbBizConfigDTO baseWbBizConfigDTO = new BaseWbBizConfigDTO();
            baseWbBizConfigDTO.setWbCode(invTaskStartDto.getWbCode());
            List<BaseWbBizConfigDTO> baseWbBizConfigDTOList = baseWbBizConfigService.selectSelective(baseWbBizConfigDTO);
            invTaskStartDto.setInvTaskCount(Integer.valueOf(baseWbBizConfigDTOList.get(0).getBizBatchMaxNum()));
        }
        //找出没有任务的bincode 对应的货架 进行盘点
        binCodeList.stream().filter(p -> ObjectUtils.isEmpty(basePodDetailService.judgeBincodeIfTask(p)));
        if (ObjectUtils.isEmpty(binCodeList)) {
            throw new BusinessException("当前盘点任务下的货架 都在执行任务 请稍后盘点");
        }
        List<String> newBinCodeList = binCodeList.stream().limit(invTaskStartDto.getInvTaskCount()).collect(Collectors.toList());
        //集成呼叫功能（盘点）
        iCommonService.checkWbCodeIfAllowCreateTaskByWbCodeAndTaskType(invTaskStartDto.getWbCode(), "inventory");


        GetOutPodDTO getOutPodDTO = new GetOutPodDTO();
        getOutPodDTO.setBincodes(newBinCodeList);
        getOutPodDTO.setTaskType("inventory");
        getOutPodDTO.setBizOrderCode(invTaskStartDto.getSrcInvNo());
        getOutPodDTO.setWbCode(invTaskStartDto.getWbCode());
        IGetOutPodService.getOutPod(getOutPodDTO);
        return new Result(newBinCodeList);
    }
}
