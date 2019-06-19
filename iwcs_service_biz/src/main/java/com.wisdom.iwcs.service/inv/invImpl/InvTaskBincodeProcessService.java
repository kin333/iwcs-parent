package com.wisdom.iwcs.service.inv.invImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.inv.InvTaskBincodeProcess;
import com.wisdom.iwcs.domain.inv.dto.InvTaskBincodeProcessDTO;
import com.wisdom.iwcs.mapper.inv.InvTaskBincodeProcessMapper;
import com.wisdom.iwcs.mapstruct.inv.InvTaskBincodeProcessMapStruct;
import com.wisdom.iwcs.service.inv.IinvTaskBincodeProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class InvTaskBincodeProcessService implements IinvTaskBincodeProcessService {
    private final Logger logger = LoggerFactory.getLogger(InvTaskBincodeProcessService.class);

    private final InvTaskBincodeProcessMapper invTaskBincodeProcessMapper;

    private final InvTaskBincodeProcessMapStruct invTaskBincodeProcessMapStruct;

    @Autowired
    public InvTaskBincodeProcessService(InvTaskBincodeProcessMapStruct invTaskBincodeProcessMapStruct, InvTaskBincodeProcessMapper invTaskBincodeProcessMapper) {
        this.invTaskBincodeProcessMapStruct = invTaskBincodeProcessMapStruct;
        this.invTaskBincodeProcessMapper = invTaskBincodeProcessMapper;
    }

    /**
     * 储存 (盘点任务中所查询的bincode) 数据
     *
     * @param record {@link InvTaskBincodeProcessDTO }
     * @return int
     */
    @Override
    public int addInvTaskBincode(InvTaskBincodeProcessDTO record) {
        InvTaskBincodeProcess invTaskBincodeProcess = invTaskBincodeProcessMapStruct.toEntity(record);
        invTaskBincodeProcess.setCreatedTime(new Date());
        invTaskBincodeProcess.setUpdateTime(new Date());
        int num = invTaskBincodeProcessMapper.insert(invTaskBincodeProcess);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return num;
    }

    /**
     * 根据唯一任务标识 task_bincode 更新 该bincode的盘点状态
     *
     * @param record {@link InvTaskBincodeProcessDTO }
     * @return @int
     */
    @Override
    public int updateInvBincodeStatus(InvTaskBincodeProcessDTO record) {
        InvTaskBincodeProcess invTaskBincodeProcess = invTaskBincodeProcessMapStruct.toEntity(record);
        int num = invTaskBincodeProcessMapper.updateInvBincodeStatus(invTaskBincodeProcess);
        if (num < 1) {
            throw new BusinessException("这个" + record.getSrcInvNo() + " 任务下的" + record.getTaskBincode() + "已经盘点过");
        }
        return num;
    }

    /**
     * 查询Inv任务的剩余Bincode
     *
     * @param srcInvNo
     * @return
     */
    public List<String> queryTheRemainingBincodeOfInvTask(String srcInvNo) {

        List<InvTaskBincodeProcess> invTaskBincodeProcessList = invTaskBincodeProcessMapper.queryTheRemainingBincodeOfInvTask(srcInvNo);
        List<String> bincodeList = invTaskBincodeProcessList.stream().map(InvTaskBincodeProcess::getTaskBincode).collect(Collectors.toList());
        return bincodeList;
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<InvTaskBincodeProcessDTO> }
     */
    @Override
    public GridReturnData<InvTaskBincodeProcessDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<InvTaskBincodeProcessDTO> mGridReturnData = new GridReturnData<>();
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

        List<InvTaskBincodeProcess> list = invTaskBincodeProcessMapper.selectPage(map);

        PageInfo<InvTaskBincodeProcess> pageInfo = new PageInfo<>(list);
        PageInfo<InvTaskBincodeProcessDTO> pageInfoFinal = new PageInfo<>(invTaskBincodeProcessMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 通过bincode的任务 查询
     */
    public List<InvTaskBincodeProcess> inquiryBincodeTask(String bincode) {
        return invTaskBincodeProcessMapper.inquiryBincodeTask(bincode);


    }

    public List<InvTaskBincodeProcess> selectInvTaskBincodeProcess(String srcInvNo) {
        Preconditions.checkBusinessError(srcInvNo == null || srcInvNo.isEmpty(), "缺少上游盘点号");
        List<InvTaskBincodeProcess> list = invTaskBincodeProcessMapper.selectProcessBySrcInvNo(srcInvNo);
        return list;
    }
}
