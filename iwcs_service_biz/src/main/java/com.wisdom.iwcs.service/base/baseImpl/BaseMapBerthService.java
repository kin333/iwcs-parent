package com.wisdom.iwcs.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTO;
import com.wisdom.iwcs.domain.base.dto.BaseMapUpdateAreaDTO;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapstruct.base.BaseMapBerthMapStruct;
import com.wisdom.iwcs.service.base.IBaseMapBerthService;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class BaseMapBerthService implements IBaseMapBerthService{
    private final Logger logger = LoggerFactory.getLogger(BaseMapBerthService.class);

    private final BaseMapBerthMapper baseMapBerthMapper;

    private final BaseMapBerthMapStruct baseMapBerthMapStruct;

    @Autowired
    public BaseMapBerthService(BaseMapBerthMapStruct baseMapBerthMapStruct, BaseMapBerthMapper baseMapBerthMapper) {
        this.baseMapBerthMapStruct = baseMapBerthMapStruct;
        this.baseMapBerthMapper = baseMapBerthMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BaseMapBerthDTO }
     * @return int
     */
    @Override
    public int insert(BaseMapBerthDTO record) {
        BaseMapBerth baseMapBerth = baseMapBerthMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMapBerth.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        baseMapBerth.setCreatedTime(new Date());
        baseMapBerth.setCreatedBy(userId);
        baseMapBerth.setLastModifiedBy(userId);
        baseMapBerth.setLastModifiedTime(new Date());

        int num = baseMapBerthMapper.insert(baseMapBerth);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BaseMapBerthDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BaseMapBerthDTO> records) {
        List<BaseMapBerth> recordList = baseMapBerthMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = baseMapBerthMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BaseMapBerthDTO }
     */
    @Override
    public BaseMapBerthDTO selectByPrimaryKey(Integer id) {

        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseMapBerth, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseMapBerthMapStruct.toDto(baseMapBerth);
    }

    /**
     * 根据point_alias字段查询记录
     * @param pointAlias
     * @return
     */
    @Override
    public BaseMapBerthDTO selectByPointAlias(String pointAlias) {
        List<BaseMapBerth> baseMapBerth = baseMapBerthMapper.selectByPointAliass(pointAlias);
        Preconditions.checkBusinessError(baseMapBerth == null, pointAlias+"该别名对应的点位数据不存在!");
        Preconditions.checkBusinessError(baseMapBerth.size()>1, pointAlias+"该别名对应的点位数据不唯一!");
        return baseMapBerthMapStruct.toDto(baseMapBerth.get(0));
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BaseMapBerthDTO }
     * @return {@link List<BaseMapBerthDTO> }
     */
    @Override
    public List<BaseMapBerthDTO> selectSelective(BaseMapBerthDTO record) {
        BaseMapBerth baseMapBerth = baseMapBerthMapStruct.toEntity(record);

        List<BaseMapBerth> baseMapBerthList = baseMapBerthMapper.select(baseMapBerth);
        return baseMapBerthMapStruct.toDto(baseMapBerthList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BaseMapBerthDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BaseMapBerthDTO record) {
        BaseMapBerth baseMapBerth = baseMapBerthMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMapBerth.setLastModifiedBy(userId);
        baseMapBerth.setLastModifiedTime(new Date());

        int num = baseMapBerthMapper.updateByPrimaryKey(baseMapBerth);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BaseMapBerthDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BaseMapBerthDTO record) {
        BaseMapBerth baseMapBerth = baseMapBerthMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMapBerth.setLastModifiedBy(userId);
        baseMapBerth.setLastModifiedTime(new Date());

        int num = baseMapBerthMapper.updateByPrimaryKeySelective(baseMapBerth);
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
        int num = baseMapBerthMapper.deleteByPrimaryKey(id);
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
        return baseMapBerthMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return baseMapBerthMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return baseMapBerthMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BaseMapBerthDTO> }
     */
    @Override
    public GridReturnData<BaseMapBerthDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BaseMapBerthDTO> mGridReturnData = new GridReturnData<>();
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

        List<BaseMapBerth> list = baseMapBerthMapper.selectPage(map);

        PageInfo<BaseMapBerth> pageInfo = new PageInfo<>(list);
        PageInfo<BaseMapBerthDTO> pageInfoFinal = new PageInfo<>(baseMapBerthMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 锁定地图资源
     *
     * @param berCode
     * @param podcode
     * @return
     */
    public boolean lockMapBerth(String berCode, String podcode, String lockSource) {
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(berCode);
        Integer inLock = baseMapBerth.getInLock();
        if (inLock == 0) {
            baseMapBerth.setInLock(1);
            baseMapBerth.setLastModifiedTime(new Date());
            baseMapBerth.setPodCode(podcode);
            baseMapBerth.setLockSource(lockSource);
            //TODO 锁资源添加乐观锁机制
            baseMapBerthMapper.updateByPrimaryKey(baseMapBerth);
            return true;
        }
        return false;
    }

    /**
     * 解锁地图资源
     *
     * @param berCode
     * @param podcode
     * @return
     */
    public boolean unlockMapBerth(String berCode, String podcode, String lockSource) {
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(berCode);
        baseMapBerth.setInLock(0);
        baseMapBerth.setPodCode(null);
        baseMapBerth.setLockSource(null);
        baseMapBerthMapper.updateByPrimaryKey(baseMapBerth);
        return false;
    }

    /**
     * 根据地图编号查询全部储位
     * @param baseMapBerthDTO
     * @return
     */
    @Override
    public List<BaseMapBerthDTO> selectAlltorageInfo(BaseMapBerthDTO baseMapBerthDTO){
        List<BaseMapBerth> baseMapBerthList = baseMapBerthMapper.selectAlltorageByMapCode(baseMapBerthDTO.getAreaCode(),baseMapBerthDTO.getOperateAreaCode());
        List<BaseMapBerthDTO> baseMapBerthDTOList = new ArrayList<>();
        baseMapBerthList.forEach(baseMapBerth -> {
            BaseMapBerthDTO resultBaseMapBerthDTO = baseMapBerthMapStruct.toDto(baseMapBerth);
            if(!Strings.isNullOrEmpty(baseMapBerth.getPodCode()) || (baseMapBerth.getInLock() != null && baseMapBerth.getInLock() ==1)) {
                resultBaseMapBerthDTO.setUseEnable(0);
            }else {
                resultBaseMapBerthDTO.setUseEnable(1);
            }
            baseMapBerthDTOList.add(resultBaseMapBerthDTO);
        });
        return baseMapBerthDTOList;
    }

    /**
     * 根据berCode修改点位的特定字段
     * @param baseMapBerthDTOList
     * @return
     */
    @Override
    public int updateByBerCode(List<BaseMapBerthDTO> baseMapBerthDTOList) {
        if (baseMapBerthDTOList==null||baseMapBerthDTOList.size()==0) {
            return 0;
        }
        String pointAlias = baseMapBerthDTOList.get(0).getPointAlias();
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(pointAlias);
        if (baseMapBerth != null) {
            return 500;
        }
        return baseMapBerthMapper.updateListByBerCode(baseMapBerthDTOList);
    }

    @Override
    public List<BaseMapBerth> selectMapDataByMapCode(BaseMapBerth baseMapBerth) {
        List<BaseMapBerth> baseMapBerthList = baseMapBerthMapper.selectBerthCodeByMapCode(baseMapBerth.getMapCode());
        return baseMapBerthList;
    }

    @Override
    public BaseMapBerth selectMapDataByBerCode(BaseMapBerth baseMapBerth) {
        BaseMapBerth baseMapBerths = baseMapBerthMapper.selectMapDataByBerCode(baseMapBerth);

        return baseMapBerths;
    }

    @Override
    public int updateMapById(BaseMapUpdateAreaDTO record) {
        if (!record.getOperateAreaBool()) {
            record.setOperateAreaCode("");
        }
        if (!record.getBizTypeBool()) {
            record.setBizType("");
        }
        if (!record.getBizSecondAreaBool()) {
            record.setBizSecondAreaCode("");
        }
        List<Integer> id = record.getId();
        int num = baseMapBerthMapper.updateMapById(record, id);

        return num;
    }

    @Override
    public int updateMapBerthById(List<BaseMapBerthDTO> baseMapBerthDTO) {

        int num = baseMapBerthMapper.updateMapBerthById(baseMapBerthDTO);
        return num;
    }

}
