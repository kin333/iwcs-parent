package com.wisdom.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.base.BasePodLayerStkMapStruct;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BasePodLayerStk;
import com.wisdom.iwcs.domain.base.dto.BasePodLayerStkDTO;
import com.wisdom.iwcs.mapper.base.BasePodLayerStkMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.base.IBasePodLayerStkService;
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
public class BasePodLayerStkService implements IBasePodLayerStkService {
    private final Logger logger = LoggerFactory.getLogger(BasePodLayerStkService.class);

    private final BasePodLayerStkMapper basePodLayerStkMapper;

    private final BasePodLayerStkMapStruct basePodLayerStkMapStruct;

    @Autowired
    public BasePodLayerStkService(BasePodLayerStkMapStruct basePodLayerStkMapStruct, BasePodLayerStkMapper basePodLayerStkMapper) {
        this.basePodLayerStkMapStruct = basePodLayerStkMapStruct;
        this.basePodLayerStkMapper = basePodLayerStkMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BasePodLayerStkDTO }
     * @return int
     */
    @Override
    public int insert(BasePodLayerStkDTO record) {
        BasePodLayerStk basePodLayerStk = basePodLayerStkMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        basePodLayerStk.setLastModifiedTime(new Date());

        int num = basePodLayerStkMapper.insert(basePodLayerStk);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BasePodLayerStkDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BasePodLayerStkDTO> records) {
        List<BasePodLayerStk> recordList = basePodLayerStkMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setLastModifiedTime(new Date());
        });

        int num = basePodLayerStkMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BasePodLayerStkDTO }
     */
    @Override
    public BasePodLayerStkDTO selectByPrimaryKey(Integer id) {

        BasePodLayerStk basePodLayerStk = basePodLayerStkMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(basePodLayerStk, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return basePodLayerStkMapStruct.toDto(basePodLayerStk);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BasePodLayerStkDTO }
     * @return {@link List<BasePodLayerStkDTO> }
     */
    @Override
    public List<BasePodLayerStkDTO> selectSelective(BasePodLayerStkDTO record) {
        BasePodLayerStk basePodLayerStk = basePodLayerStkMapStruct.toEntity(record);

        List<BasePodLayerStk> basePodLayerStkList = basePodLayerStkMapper.select(basePodLayerStk);
        return basePodLayerStkMapStruct.toDto(basePodLayerStkList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BasePodLayerStkDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BasePodLayerStkDTO record) {
        BasePodLayerStk basePodLayerStk = basePodLayerStkMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        basePodLayerStk.setLastModifiedTime(new Date());

        int num = basePodLayerStkMapper.updateByPrimaryKey(basePodLayerStk);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BasePodLayerStkDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BasePodLayerStkDTO record) {
        BasePodLayerStk basePodLayerStk = basePodLayerStkMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        basePodLayerStk.setLastModifiedTime(new Date());

        int num = basePodLayerStkMapper.updateByPrimaryKeySelective(basePodLayerStk);
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
        int num = basePodLayerStkMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }


    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return basePodLayerStkMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BasePodLayerStkDTO> }
     */
    @Override
    public GridReturnData<BasePodLayerStkDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BasePodLayerStkDTO> mGridReturnData = new GridReturnData<>();
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

        List<BasePodLayerStk> list = basePodLayerStkMapper.selectPage(map);

        PageInfo<BasePodLayerStk> pageInfo = new PageInfo<>(list);
        PageInfo<BasePodLayerStkDTO> pageInfoFinal = new PageInfo<>(basePodLayerStkMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
