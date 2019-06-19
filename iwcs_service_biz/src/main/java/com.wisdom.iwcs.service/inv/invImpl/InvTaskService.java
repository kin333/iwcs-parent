package com.wisdom.iwcs.service.inv.invImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.inv.InvTask;
import com.wisdom.iwcs.domain.inv.dto.*;
import com.wisdom.iwcs.domain.stock.Stock;
import com.wisdom.iwcs.mapper.inv.InvTaskMapper;
import com.wisdom.iwcs.mapstruct.inv.InvTaskCondDetailDTOOrStockMapStruct;
import com.wisdom.iwcs.mapstruct.inv.InvTaskMapStruct;
import com.wisdom.iwcs.service.inv.IinvTaskService;
import com.wisdom.iwcs.service.stock.impl.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class InvTaskService implements IinvTaskService {
    private final Logger logger = LoggerFactory.getLogger(InvTaskService.class);

    private final InvTaskMapper invTaskMapper;

    private final InvTaskMapStruct invTaskMapStruct;
    @Autowired
    StockService stockService;
    @Autowired
    InvTaskBincodeProcessService invTaskBincodeProcessService;
    @Autowired
    InvTaskCondDetailDTOOrStockMapStruct invTaskCondDetailDTOOrStockMapStruct;
    @Autowired
    InvTaskCondDetailService invTaskCondDetailService;

    @Autowired
    InvTaskDetailService invTaskDetailService;

    @Autowired
    public InvTaskService(InvTaskMapStruct invTaskMapStruct, InvTaskMapper invTaskMapper) {
        this.invTaskMapStruct = invTaskMapStruct;
        this.invTaskMapper = invTaskMapper;
    }

    /**
     * 生成盘点任务
     *
     * @param record {@link InvTaskDTO }
     * @return int
     */
    @Override
    public List<String> createInvTask(InvTaskDTO record) {
        InvTask invTask = invTaskMapStruct.toEntity(record);
        //对任务天数进行判断 todo 假的库存周期
        final int virtualCycle = 30;
        if ((!ObjectUtils.isEmpty(record.getDays())) && (virtualCycle <= record.getDays())) {
            throw new BusinessException("当前选择的天数大于库存归档的周期 暂不支持");
        }
        //保存盘点任务信息
        int num = invTaskMapper.saveInvTaskInfo(invTask);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        //把唯一任务标识 加到条件中 作为唯一标识
        for (InvTaskCondDetailDTO a : record.getData()) {
            a.setInvNum(record.getSrcInvNo());
            a.setDays(record.getDays());
        }
        //储存盘点条件
        invTaskCondDetailService.storageInvConditionBatch(record.getData());
        List<String> bincodeList = new ArrayList<>();
        //对 days 进行判定 传 0 等于1天内
        if (ObjectUtils.isEmpty(record.getDays())) {
            record.setDays(0);
        } else {
            record.setDays(record.getDays() + 1);
        }

        //通过盘点任务的条件 去查询库存表 得到相应的bincode
        for (InvTaskCondDetailDTO taskCondition : record.getData()) {
            List<Stock> stockList = stockService.invTaskCondQuery(taskCondition, new Long(record.getDays() * 24 * 60 * 60));
            stockList.stream().forEach(
                    data -> bincodeList.add(data.getBincode())
            );
        }
        InvTaskBincodeProcessDTO invTaskBincodeProcess = new InvTaskBincodeProcessDTO();
        invTaskBincodeProcess.setSrcInvNo(record.getSrcInvNo());
        invTaskBincodeProcess.setCreatedBy(record.getSrcUserCode());
        invTaskBincodeProcess.setInvStatus(0);
        invTaskBincodeProcess.setIfOut(0);

        bincodeList.stream().distinct().forEach(bincode -> {
            invTaskBincodeProcess.setTaskBincode(bincode);
            invTaskBincodeProcessService.addInvTaskBincode(invTaskBincodeProcess);
        });
        if (!ObjectUtils.isEmpty(record.getAutomatedCallType())) {
            InvTaskStartDto invTaskStartDto = new InvTaskStartDto();
            invTaskStartDto.setWbCode(record.getWbCode());
            invTaskStartDto.setSrcInvNo(record.getSrcInvNo());
            invTaskDetailService.startInvTask(invTaskStartDto);
        }
        return bincodeList;
    }

    /**
     * 条件查询InvTask
     *
     * @param record {@link InvTaskDTO }
     * @return {@link List<InvTaskDTO> }
     */
    @Override
    public List<InvTaskDTO> criteriaQueryInvTask(InvTaskDTO record) {
        InvTask invTask = invTaskMapStruct.toEntity(record);
        List<InvTask> invTaskList = invTaskMapper.select(invTask);
        return invTaskMapStruct.toDto(invTaskList);
    }


    @Override
    public int deleteByPrimaryKey(Integer id) {
        int num = invTaskMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);
        return num;
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<InvTaskDTO> }
     */
    @Override
    public GridReturnData<InvTaskDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<InvTaskDTO> mGridReturnData = new GridReturnData<>();
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

        List<InvTask> list = invTaskMapper.selectPage(map);

        PageInfo<InvTask> pageInfo = new PageInfo<>(list);
        PageInfo<InvTaskDTO> pageInfoFinal = new PageInfo<>(invTaskMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 更新盘点状态
     * 更新invtask表中的盘点状态
     */
    public int updateTheFinalStatusOfInvTask(InvTask invTask) {

        int num = invTaskMapper.updateTheFinalStatusOfInvTask(invTask);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);
        return num;
    }

    /**
     * @param invTaskSearchDto
     * @return
     */
    @Override
    public List<InvTask> selectInvTask(InvTaskSearchDto invTaskSearchDto) {
        List<InvTask> invTaskList = invTaskMapper.selectInvTask(invTaskSearchDto);
        return invTaskList;
    }
}
