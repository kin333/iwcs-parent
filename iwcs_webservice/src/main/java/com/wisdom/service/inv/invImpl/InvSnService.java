package com.wisdom.service.inv.invImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.inv.InvSnMapStruct;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.inv.InvSn;
import com.wisdom.iwcs.domain.inv.dto.InvSnDTO;
import com.wisdom.iwcs.mapper.inv.InvSnMapper;
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
public class InvSnService implements com.wisdom.service.inv.IinvSnService {
    private final Logger logger = LoggerFactory.getLogger(InvSnService.class);

    private final InvSnMapper invSnMapper;

    private final InvSnMapStruct invSnMapStruct;

    @Autowired
    public InvSnService(InvSnMapStruct invSnMapStruct, InvSnMapper invSnMapper) {
        this.invSnMapStruct = invSnMapStruct;
        this.invSnMapper = invSnMapper;
    }

    /**
     * 添加盘点SN信息
     *
     * @param record {@link InvSnDTO }
     * @return int
     */
    @Override
    public int addInvSnDetail(InvSnDTO record) {
        InvSn invSn = invSnMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        invSn.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        invSn.setCreatedTime(new Date());
        invSn.setCreatedBy(userId);
        invSn.setLastModifiedBy(userId);
        invSn.setLastModifiedTime(new Date());

        int num = invSnMapper.insert(invSn);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<InvSnDTO> }
     */
    @Override
    public GridReturnData<InvSnDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<InvSnDTO> mGridReturnData = new GridReturnData<>();
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

        List<InvSn> list = invSnMapper.selectPage(map);

        PageInfo<InvSn> pageInfo = new PageInfo<>(list);
        PageInfo<InvSnDTO> pageInfoFinal = new PageInfo<>(invSnMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 更新盘点数量 (Sn)
     *
     * @param invSn
     * @return
     */
    @Override
    public int updateInvSNResults(InvSn invSn) {
        Integer userId = SecurityUtils.getCurrentUserId();
        invSn.setLastModifiedBy(userId);
        invSn.setLastModifiedTime(new Date());
        int num = invSnMapper.updateInvSNResults(invSn);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return num;
    }
}
