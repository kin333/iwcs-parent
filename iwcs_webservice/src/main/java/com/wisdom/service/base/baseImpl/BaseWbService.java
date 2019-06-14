package com.wisdom.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.base.BaseWbMapStruct;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseWb;
import com.wisdom.iwcs.domain.base.dto.BaseWbDTO;
import com.wisdom.iwcs.mapper.base.BaseWbMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.base.IBaseWbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wisdom.iwcs.common.utils.DeleteFlagEnum.NOT_DELETED;

@Service
@Transactional(rollbackFor = Exception.class)
public class BaseWbService implements IBaseWbService {
    private final Logger logger = LoggerFactory.getLogger(BaseWbService.class);

    private final BaseWbMapper baseWbMapper;

    private final BaseWbMapStruct baseWbMapStruct;

    @Autowired
    public BaseWbService(BaseWbMapStruct baseWbMapStruct, BaseWbMapper baseWbMapper) {
        this.baseWbMapStruct = baseWbMapStruct;
        this.baseWbMapper = baseWbMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BaseWbDTO }
     * @return int
     */
    @Override
    public int insert(BaseWbDTO record) {
        BaseWb baseWb = baseWbMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWb.setDeleteFlag(NOT_DELETED.getStatus());
        baseWb.setCreatedTime(new Date());
        baseWb.setCreatedBy(userId);
        baseWb.setLastModifiedBy(userId);
        baseWb.setLastModifiedTime(new Date());

        int num = baseWbMapper.insert(baseWb);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BaseWbDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BaseWbDTO> records) {
        List<BaseWb> recordList = baseWbMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = baseWbMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BaseWbDTO }
     */
    @Override
    public BaseWbDTO selectByPrimaryKey(Integer id) {

        BaseWb baseWb = baseWbMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseWb, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseWbMapStruct.toDto(baseWb);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BaseWbDTO }
     * @return {@link List<BaseWbDTO> }
     */
    @Override
    public List<BaseWbDTO> selectSelective(BaseWbDTO record) {
        BaseWb baseWb = baseWbMapStruct.toEntity(record);

        List<BaseWb> baseWbList = baseWbMapper.select(baseWb);
        return baseWbMapStruct.toDto(baseWbList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BaseWbDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BaseWbDTO record) {
        BaseWb baseWb = baseWbMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWb.setLastModifiedBy(userId);
        baseWb.setLastModifiedTime(new Date());

        int num = baseWbMapper.updateByPrimaryKey(baseWb);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BaseWbDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BaseWbDTO record) {
        BaseWb baseWb = baseWbMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWb.setLastModifiedBy(userId);
        baseWb.setLastModifiedTime(new Date());

        int num = baseWbMapper.updateByPrimaryKeySelective(baseWb);
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
        int num = baseWbMapper.deleteByPrimaryKey(id);
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
        return baseWbMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return baseWbMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return baseWbMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BaseWbDTO> }
     */
    @Override
    public GridReturnData<BaseWbDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BaseWbDTO> mGridReturnData = new GridReturnData<>();
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

        List<BaseWb> list = baseWbMapper.selectPage(map);

        PageInfo<BaseWb> pageInfo = new PageInfo<>(list);
        PageInfo<BaseWbDTO> pageInfoFinal = new PageInfo<>(baseWbMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    @Override
    public Result getFreeWbInfo() {
        List<BaseWb> freeBaseWbInfoList = baseWbMapper.selectFreeWbInfoByValidFlagAndDeleteFlag(ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
        List<BaseWbDTO> returnFreeWbList = baseWbMapStruct.toDto(freeBaseWbInfoList);
        return new Result(returnFreeWbList);
    }
}
