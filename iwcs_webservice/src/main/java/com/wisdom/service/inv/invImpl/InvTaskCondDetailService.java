package com.wisdom.service.inv.invImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.inv.InvTaskCondDetailMapStruct;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.inv.InvTaskCondDetail;
import com.wisdom.iwcs.domain.inv.dto.InvTaskCondDetailDTO;
import com.wisdom.iwcs.mapper.inv.InvTaskCondDetailMapper;
import com.wisdom.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class InvTaskCondDetailService implements com.wisdom.service.inv.IinvTaskCondDetailService {
    private final Logger logger = LoggerFactory.getLogger(InvTaskCondDetailService.class);

    private final InvTaskCondDetailMapper invTaskCondDetailMapper;

    private final InvTaskCondDetailMapStruct invTaskCondDetailMapStruct;

    @Autowired
    public InvTaskCondDetailService(InvTaskCondDetailMapStruct invTaskCondDetailMapStruct, InvTaskCondDetailMapper invTaskCondDetailMapper) {
        this.invTaskCondDetailMapStruct = invTaskCondDetailMapStruct;
        this.invTaskCondDetailMapper = invTaskCondDetailMapper;
    }

    /**
     * storageInvCondition
     * 存储盘点条件
     *
     * @param record {@link InvTaskCondDetailDTO }
     * @return int
     */
    @Override
    public int storageInvCondition(InvTaskCondDetailDTO record) {
        InvTaskCondDetail invTaskCondDetail = invTaskCondDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        invTaskCondDetail.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        invTaskCondDetail.setCreatedTime(new Date());
        invTaskCondDetail.setCreatedBy(userId);
        invTaskCondDetail.setLastModifiedBy(userId);
        invTaskCondDetail.setLastModifiedTime(new Date());

        int num = invTaskCondDetailMapper.insert(invTaskCondDetail);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量存储盘点条件
     *
     * @param records {@link List<InvTaskCondDetailDTO> }
     * @return int
     */
    @Override
    public int storageInvConditionBatch(List<InvTaskCondDetailDTO> records) {
        List<InvTaskCondDetail> recordList = invTaskCondDetailMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = invTaskCondDetailMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<InvTaskCondDetailDTO> }
     */
    @Override
    public GridReturnData<InvTaskCondDetailDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<InvTaskCondDetailDTO> mGridReturnData = new GridReturnData<>();
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

        List<InvTaskCondDetail> list = invTaskCondDetailMapper.selectPage(map);

        PageInfo<InvTaskCondDetail> pageInfo = new PageInfo<>(list);
        PageInfo<InvTaskCondDetailDTO> pageInfoFinal = new PageInfo<>(invTaskCondDetailMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 盘点任务条件明细--单条
     *
     * @param invNum
     * @return
     */
    @Override
    public List<InvTaskCondDetail> selectInvTaskCondDetail(String invNum) {

        Preconditions.checkBusinessError(invNum == null || invNum.isEmpty(), "缺少盘点任务编号");

        List<InvTaskCondDetail> invTaskCondDetailList = invTaskCondDetailMapper.selectInvTaskCondDetail(invNum);
        return invTaskCondDetailList;

    }

    /**
     * 通过srcInvNo bincode 获取相应的条件数据
     */
    @Override
    public InvTaskCondDetail getCorrespondCondInfo(String srcInvNo) {
        return invTaskCondDetailMapper.getCorrespondCondInfo(srcInvNo);
    }
}
