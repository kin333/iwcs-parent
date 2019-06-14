package com.wisdom.service.codec;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.codec.AreaMapStruct;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.codec.Area;
import com.wisdom.iwcs.domain.codec.dto.AreaDto;
import com.wisdom.iwcs.mapper.codec.AreaMapper;
import com.wisdom.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class AreaService {
    private final Logger logger = LoggerFactory.getLogger(AreaService.class);

    @Autowired
    AreaMapper areaMapper;

    @Autowired
    AreaMapStruct areaMapStruct;

    /**
     * 写入记录
     *
     * @param record { @link AreaDto }
     * @return int
     */
    public int insert(AreaDto record) {
        Area area = areaMapStruct.toEntity(record);
        int num = areaMapper.insert(area);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records { @link List<AreaDto> }
     * @return int
     */
    public int insertBatch(List<AreaDto> records) {
        List<Area> recordList = areaMapStruct.toEntity(records);
        int num = areaMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id { @link Integer }
     * @return { @link AreaDto }
     */
    public AreaDto selectByPrimaryKey(Integer id) {

        Area area = areaMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(area, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return areaMapStruct.toDto(area);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record { @link AreaDto }
     * @return { @link List<AreaDto> }
     */
    public List<AreaDto> selectSelective(AreaDto record) {
        Area area = areaMapStruct.toEntity(record);

        List<Area> areaList = areaMapper.select(area);
        return areaMapStruct.toDto(areaList);
    }

    /**
     * 根据主键更新
     *
     * @param record { @link AreaDto }
     * @return int
     */
    public int updateByPrimaryKey(AreaDto record) {
        Area area = areaMapStruct.toEntity(record);
        int num = areaMapper.updateByPrimaryKey(area);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record { @link AreaDto }
     * @return int
     */
    public int updateByPrimaryKeySelective(AreaDto record) {
        Area area = areaMapStruct.toEntity(record);


        int num = areaMapper.updateByPrimaryKeySelective(area);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @param id { @link Integer }
     * @return int
     */
    public int deleteByPrimaryKey(Integer id) {
        int num = areaMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }


    /**
     * 根据主键删除多条记录
     *
     * @param ids { @link List<String> }
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return areaMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest { @link GridPageRequest }
     * @return { @link GridReturnData<AreaDto> }
     */
    public GridReturnData<AreaDto> selectPage(GridPageRequest gridPageRequest) {
        Integer user_id = SecurityUtils.getCurrentUserId();

        GridReturnData<AreaDto> mGridReturnData = new GridReturnData<>();
        List<GridFilterInfo> filterList = gridPageRequest.getFilterList();
        Map<String, Object> map = new HashMap<>();
        filterList.stream().forEach(gridFilterInfo -> {//封装筛选条件
            if (gridFilterInfo.getFilterKey() != null && gridFilterInfo.getFilterValue() != null) {
                map.put(gridFilterInfo.getFilterKey(), gridFilterInfo.getFilterValue());
            }
        });
        map.put("searchKey", gridPageRequest.getSearchKey());
        //对map中的参数的合法性进行校验

        String sortMyBatisByString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMyBatisByString);

        List<Area> list = areaMapper.selectByMap(map);

        PageInfo<Area> pageInfo = new PageInfo<>(list);
        PageInfo<AreaDto> pageInfoFinal = new PageInfo<>(areaMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    public Result getAreaListByLevel(Integer level) {
        List<Area> areaListByLevel = areaMapper.getAreaListByLevel(level);
        List<AreaDto> areaDtos = areaMapStruct.toDto(areaListByLevel);
        areaDtos.forEach(areaDto -> {
            areaDto.setValue(areaDto.getId());
            areaDto.setLabel(areaDto.getName());
        });
        return new Result(areaDtos);
    }

    public Result getAreaListByParentId(Integer parentId) {
        List<Area> areaList = areaMapper.getAreaListByParentId(parentId);
        List<AreaDto> areaDtos = areaMapStruct.toDto(areaList);
        areaDtos.forEach(areaDto -> {
            areaDto.setValue(areaDto.getId());
            areaDto.setLabel(areaDto.getName());
        });

        return new Result(areaDtos);
    }
}
