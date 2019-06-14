package com.wisdom.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.base.BaseBincodeDetailMapStruct;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseBincodeDetail;
import com.wisdom.iwcs.domain.base.dto.BaseBincodeDetailDTO;
import com.wisdom.iwcs.mapper.base.BaseBincodeDetailMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.base.IBaseBincodeDetailService;
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
public class BaseBincodeDetailService implements IBaseBincodeDetailService {
    private final Logger logger = LoggerFactory.getLogger(BaseBincodeDetailService.class);

    private final BaseBincodeDetailMapper baseBincodeDetailMapper;

    private final BaseBincodeDetailMapStruct baseBincodeDetailMapStruct;

    @Autowired
    public BaseBincodeDetailService(BaseBincodeDetailMapStruct baseBincodeDetailMapStruct, BaseBincodeDetailMapper baseBincodeDetailMapper) {
        this.baseBincodeDetailMapStruct = baseBincodeDetailMapStruct;
        this.baseBincodeDetailMapper = baseBincodeDetailMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BaseBincodeDetailDTO }
     * @return int
     */
    @Override
    public int insert(BaseBincodeDetailDTO record) {
        BaseBincodeDetail baseBincodeDetail = baseBincodeDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseBincodeDetail.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        baseBincodeDetail.setCreatedTime(new Date());
        baseBincodeDetail.setCreatedBy(userId);
        baseBincodeDetail.setLastModifiedBy(userId);
        baseBincodeDetail.setLastModifiedTime(new Date());

        int num = baseBincodeDetailMapper.insert(baseBincodeDetail);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BaseBincodeDetailDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BaseBincodeDetailDTO> records) {
        List<BaseBincodeDetail> recordList = baseBincodeDetailMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = baseBincodeDetailMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BaseBincodeDetailDTO }
     */
    @Override
    public BaseBincodeDetailDTO selectByPrimaryKey(Integer id) {

        BaseBincodeDetail baseBincodeDetail = baseBincodeDetailMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseBincodeDetail, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseBincodeDetailMapStruct.toDto(baseBincodeDetail);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BaseBincodeDetailDTO }
     * @return {@link List<BaseBincodeDetailDTO> }
     */
    @Override
    public List<BaseBincodeDetailDTO> selectSelective(BaseBincodeDetailDTO record) {
        BaseBincodeDetail baseBincodeDetail = baseBincodeDetailMapStruct.toEntity(record);

        List<BaseBincodeDetail> baseBincodeDetailList = baseBincodeDetailMapper.select(baseBincodeDetail);
        return baseBincodeDetailMapStruct.toDto(baseBincodeDetailList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BaseBincodeDetailDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BaseBincodeDetailDTO record) {
        BaseBincodeDetail baseBincodeDetail = baseBincodeDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseBincodeDetail.setLastModifiedBy(userId);
        baseBincodeDetail.setLastModifiedTime(new Date());

        int num = baseBincodeDetailMapper.updateByPrimaryKey(baseBincodeDetail);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BaseBincodeDetailDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BaseBincodeDetailDTO record) {
        BaseBincodeDetail baseBincodeDetail = baseBincodeDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseBincodeDetail.setLastModifiedBy(userId);
        baseBincodeDetail.setLastModifiedTime(new Date());

        int num = baseBincodeDetailMapper.updateByPrimaryKeySelective(baseBincodeDetail);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        int num = baseBincodeDetailMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键逻辑删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    @Override
    public int deleteLogicByPrimaryKey(Integer id) {
        return baseBincodeDetailMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return baseBincodeDetailMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return baseBincodeDetailMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BaseBincodeDetailDTO> }
     */
    @Override
    public GridReturnData<BaseBincodeDetailDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BaseBincodeDetailDTO> mGridReturnData = new GridReturnData<>();
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

        List<BaseBincodeDetail> list = baseBincodeDetailMapper.selectPage(map);

        PageInfo<BaseBincodeDetail> pageInfo = new PageInfo<>(list);
        PageInfo<BaseBincodeDetailDTO> pageInfoFinal = new PageInfo<>(baseBincodeDetailMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
