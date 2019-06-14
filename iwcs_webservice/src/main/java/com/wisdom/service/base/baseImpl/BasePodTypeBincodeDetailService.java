package com.wisdom.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.base.BasePodTypeBincodeDetailMapStruct;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BasePodTypeBincodeDetail;
import com.wisdom.iwcs.domain.base.dto.BasePodTypeBincodeDetailDTO;
import com.wisdom.iwcs.mapper.base.BasePodTypeBincodeDetailMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.base.IBasePodTypeBincodeDetailService;
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
public class BasePodTypeBincodeDetailService implements IBasePodTypeBincodeDetailService {
    private final Logger logger = LoggerFactory.getLogger(BasePodTypeBincodeDetailService.class);

    private final BasePodTypeBincodeDetailMapper basePodTypeBincodeDetailMapper;

    private final BasePodTypeBincodeDetailMapStruct basePodTypeBincodeDetailMapStruct;

    @Autowired
    public BasePodTypeBincodeDetailService(BasePodTypeBincodeDetailMapStruct basePodTypeBincodeDetailMapStruct, BasePodTypeBincodeDetailMapper basePodTypeBincodeDetailMapper) {
        this.basePodTypeBincodeDetailMapStruct = basePodTypeBincodeDetailMapStruct;
        this.basePodTypeBincodeDetailMapper = basePodTypeBincodeDetailMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BasePodTypeBincodeDetailDTO }
     * @return int
     */
    @Override
    public int insert(BasePodTypeBincodeDetailDTO record) {
        BasePodTypeBincodeDetail basePodTypeBincodeDetail = basePodTypeBincodeDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        basePodTypeBincodeDetail.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        basePodTypeBincodeDetail.setCreatedTime(new Date());
        basePodTypeBincodeDetail.setCreatedBy(userId);
        basePodTypeBincodeDetail.setLastModifiedBy(userId);
        basePodTypeBincodeDetail.setLastModifiedTime(new Date());

        int num = basePodTypeBincodeDetailMapper.insert(basePodTypeBincodeDetail);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BasePodTypeBincodeDetailDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BasePodTypeBincodeDetailDTO> records) {
        List<BasePodTypeBincodeDetail> recordList = basePodTypeBincodeDetailMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = basePodTypeBincodeDetailMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BasePodTypeBincodeDetailDTO }
     */
    @Override
    public BasePodTypeBincodeDetailDTO selectByPrimaryKey(Integer id) {

        BasePodTypeBincodeDetail basePodTypeBincodeDetail = basePodTypeBincodeDetailMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(basePodTypeBincodeDetail, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return basePodTypeBincodeDetailMapStruct.toDto(basePodTypeBincodeDetail);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BasePodTypeBincodeDetailDTO }
     * @return {@link List<BasePodTypeBincodeDetailDTO> }
     */
    @Override
    public List<BasePodTypeBincodeDetailDTO> selectSelective(BasePodTypeBincodeDetailDTO record) {
        BasePodTypeBincodeDetail basePodTypeBincodeDetail = basePodTypeBincodeDetailMapStruct.toEntity(record);

        List<BasePodTypeBincodeDetail> basePodTypeBincodeDetailList = basePodTypeBincodeDetailMapper.select(basePodTypeBincodeDetail);
        return basePodTypeBincodeDetailMapStruct.toDto(basePodTypeBincodeDetailList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BasePodTypeBincodeDetailDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BasePodTypeBincodeDetailDTO record) {
        BasePodTypeBincodeDetail basePodTypeBincodeDetail = basePodTypeBincodeDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        basePodTypeBincodeDetail.setLastModifiedBy(userId);
        basePodTypeBincodeDetail.setLastModifiedTime(new Date());

        int num = basePodTypeBincodeDetailMapper.updateByPrimaryKey(basePodTypeBincodeDetail);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BasePodTypeBincodeDetailDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BasePodTypeBincodeDetailDTO record) {
        BasePodTypeBincodeDetail basePodTypeBincodeDetail = basePodTypeBincodeDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        basePodTypeBincodeDetail.setLastModifiedBy(userId);
        basePodTypeBincodeDetail.setLastModifiedTime(new Date());

        int num = basePodTypeBincodeDetailMapper.updateByPrimaryKeySelective(basePodTypeBincodeDetail);
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
        int num = basePodTypeBincodeDetailMapper.deleteByPrimaryKey(id);
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
        return basePodTypeBincodeDetailMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return basePodTypeBincodeDetailMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return basePodTypeBincodeDetailMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BasePodTypeBincodeDetailDTO> }
     */
    @Override
    public GridReturnData<BasePodTypeBincodeDetailDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BasePodTypeBincodeDetailDTO> mGridReturnData = new GridReturnData<>();
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

        List<BasePodTypeBincodeDetail> list = basePodTypeBincodeDetailMapper.selectPage(map);

        PageInfo<BasePodTypeBincodeDetail> pageInfo = new PageInfo<>(list);
        PageInfo<BasePodTypeBincodeDetailDTO> pageInfoFinal = new PageInfo<>(basePodTypeBincodeDetailMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
