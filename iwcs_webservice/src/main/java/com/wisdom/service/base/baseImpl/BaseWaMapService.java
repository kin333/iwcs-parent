package com.wisdom.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wisdom.controller.mapstruct.base.BaseWaMapMapStruct;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseMap;
import com.wisdom.iwcs.domain.base.BaseWaMap;
import com.wisdom.iwcs.domain.base.BaseWhArea;
import com.wisdom.iwcs.domain.base.dto.BaseWaMapDTO;
import com.wisdom.iwcs.domain.base.dto.MapCodeAndAreaCodeRelationDTO;
import com.wisdom.iwcs.mapper.base.*;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.base.IBaseWaMapService;
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
public class BaseWaMapService implements IBaseWaMapService {
    private final Logger logger = LoggerFactory.getLogger(BaseWaMapService.class);

    private final BaseWaMapMapper baseWaMapMapper;

    private final BaseWaMapMapStruct baseWaMapMapStruct;

    @Autowired
    private BaseWhAreaMapper baseWhAreaMapper;
    @Autowired
    private BaseMapMapper baseMapMapper;
    @Autowired
    private BaseMapSectionMapper baseMapSectionMapper;
    @Autowired
    private BaseStgTypeMapper baseStgTypeMapper;
    @Autowired
    private BaseBincodeDetailMapper baseBincodeDetailMapper;
    @Autowired
    private BasePodDetailMapper basePodDetailMapper;
    @Autowired
    private BaseWbMapper baseWbMapper;


    @Autowired
    public BaseWaMapService(BaseWaMapMapStruct baseWaMapMapStruct, BaseWaMapMapper baseWaMapMapper) {
        this.baseWaMapMapStruct = baseWaMapMapStruct;
        this.baseWaMapMapper = baseWaMapMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BaseWaMapDTO }
     * @return int
     */
    @Override
    public int insert(BaseWaMapDTO record) {
        BaseWaMap baseWaMap = baseWaMapMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWaMap.setDeleteFlag(NOT_DELETED.getStatus());
        baseWaMap.setCreatedTime(new Date());
        baseWaMap.setCreatedBy(userId);
        baseWaMap.setLastModifiedBy(userId);
        baseWaMap.setLastModifiedTime(new Date());

        int num = baseWaMapMapper.insert(baseWaMap);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BaseWaMapDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BaseWaMapDTO> records) {
        List<BaseWaMap> recordList = baseWaMapMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = baseWaMapMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BaseWaMapDTO }
     */
    @Override
    public BaseWaMapDTO selectByPrimaryKey(Integer id) {

        BaseWaMap baseWaMap = baseWaMapMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseWaMap, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseWaMapMapStruct.toDto(baseWaMap);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BaseWaMapDTO }
     * @return {@link List<BaseWaMapDTO> }
     */
    @Override
    public List<BaseWaMapDTO> selectSelective(BaseWaMapDTO record) {
        BaseWaMap baseWaMap = baseWaMapMapStruct.toEntity(record);

        List<BaseWaMap> baseWaMapList = baseWaMapMapper.select(baseWaMap);
        return baseWaMapMapStruct.toDto(baseWaMapList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BaseWaMapDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BaseWaMapDTO record) {
        BaseWaMap baseWaMap = baseWaMapMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWaMap.setLastModifiedBy(userId);
        baseWaMap.setLastModifiedTime(new Date());

        int num = baseWaMapMapper.updateByPrimaryKey(baseWaMap);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BaseWaMapDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BaseWaMapDTO record) {
        BaseWaMap baseWaMap = baseWaMapMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWaMap.setLastModifiedBy(userId);
        baseWaMap.setLastModifiedTime(new Date());

        int num = baseWaMapMapper.updateByPrimaryKeySelective(baseWaMap);
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
        int num = baseWaMapMapper.deleteByPrimaryKey(id);
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
        return baseWaMapMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return baseWaMapMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return baseWaMapMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BaseWaMapDTO> }
     */
    @Override
    public GridReturnData<BaseWaMapDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BaseWaMapDTO> mGridReturnData = new GridReturnData<>();
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

        List<BaseWaMap> list = baseWaMapMapper.selectPage(map);

        PageInfo<BaseWaMap> pageInfo = new PageInfo<>(list);
        PageInfo<BaseWaMapDTO> pageInfoFinal = new PageInfo<>(baseWaMapMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 配置库区和地图关系
     *
     * @param mapCodeAndAreaCodeRelationDTO
     * @return
     */
    @Override
    public Result configMapCodeAndAreaCodeRelation(MapCodeAndAreaCodeRelationDTO mapCodeAndAreaCodeRelationDTO) {
        checkConfigRelationParam(mapCodeAndAreaCodeRelationDTO);
        String requestMapCode = mapCodeAndAreaCodeRelationDTO.getMapCode();
        String requestAreaCode = mapCodeAndAreaCodeRelationDTO.getAreaCode();
        BaseWaMap baseWaMap = new BaseWaMap();
        baseWaMap.setMapCode(requestMapCode);
        baseWaMap.setAreaCode(requestAreaCode);
        baseWaMap.setCreatedTime(new Date());
        baseWaMapMapper.insertSelective(baseWaMap);

        baseMapSectionMapper.updateAreaCodeByMapCode(requestMapCode, requestAreaCode);
        baseStgTypeMapper.updateAreaCodeByMapCode(requestMapCode, requestAreaCode);
        baseBincodeDetailMapper.updateAreaCodeByMapCode(requestMapCode, requestAreaCode);
        basePodDetailMapper.updateAreaCodeByMapCode(requestMapCode, requestAreaCode);
        baseWbMapper.updateAreaCodeByMapCode(requestMapCode, requestAreaCode);
        return new Result();
    }

    private void checkConfigRelationParam(MapCodeAndAreaCodeRelationDTO mapCodeAndAreaCodeRelationDTO) {
        String requestMapCode = mapCodeAndAreaCodeRelationDTO.getMapCode();
        String requestAreaCode = mapCodeAndAreaCodeRelationDTO.getAreaCode();
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(requestAreaCode), "缺少库区代码");
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(requestMapCode), "缺少地图代码");
        BaseWhArea baseWhArea = baseWhAreaMapper.selectByAreaCodeAndDeleteFlag(requestAreaCode, NOT_DELETED.getStatus());
        Preconditions.checkBusinessError(baseWhArea == null, "系统无代码为" + requestAreaCode + "的库区信息，请确认");
        BaseMap baseMap = baseMapMapper.selectByMapCodeAndDeleteFlag(requestMapCode, NOT_DELETED.getStatus());
        Preconditions.checkBusinessError(baseMap == null, "系统无代码为" + requestMapCode + "的地图信息，请确认");
        BaseWaMap baseWaMap = baseWaMapMapper.selectByMapCodeAndValidFlagAndDeleteFlag(requestMapCode, ValidFlagEnum.VALID.getStatus(), NOT_DELETED.getStatus());
        Preconditions.checkBusinessError(baseWaMap != null, "地图编码：" + requestMapCode + "已和库区：" + requestAreaCode + "关联，不允许重复关联");

    }
}
