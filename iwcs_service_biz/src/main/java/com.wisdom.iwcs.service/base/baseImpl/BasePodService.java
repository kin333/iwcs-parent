package com.wisdom.iwcs.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BasePod;
import com.wisdom.iwcs.domain.base.dto.BasePodDTO;
import com.wisdom.iwcs.domain.base.dto.ShowPodInfoCondDTO;
import com.wisdom.iwcs.domain.base.dto.ShowPodInfoResultDTO;
import com.wisdom.iwcs.mapper.base.BasePodMapper;
import com.wisdom.iwcs.mapstruct.base.BasePodMapStruct;
import com.wisdom.iwcs.service.base.IBasePodDetailService;
import com.wisdom.iwcs.service.base.IBasePodService;
import com.wisdom.iwcs.service.security.SecurityUtils;
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
public class BasePodService implements IBasePodService {
    private final Logger logger = LoggerFactory.getLogger(BasePodService.class);

    private final BasePodMapper basePodMapper;

    private final BasePodMapStruct basePodMapStruct;

    @Autowired
    private IBasePodDetailService iBasePodDetailService;

    @Autowired
    public BasePodService(BasePodMapStruct basePodMapStruct, BasePodMapper basePodMapper) {
        this.basePodMapStruct = basePodMapStruct;
        this.basePodMapper = basePodMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BasePodDTO }
     * @return int
     */
    @Override
    public int insert(BasePodDTO record) {
        BasePod basePod = basePodMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        basePod.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        basePod.setCreatedTime(new Date());
        basePod.setCreatedBy(userId);
        basePod.setLastModifiedBy(userId);
        basePod.setLastModifiedTime(new Date());

        int num = basePodMapper.insert(basePod);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BasePodDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BasePodDTO> records) {
        List<BasePod> recordList = basePodMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = basePodMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BasePodDTO }
     */
    @Override
    public BasePodDTO selectByPrimaryKey(Integer id) {

        BasePod basePod = basePodMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(basePod, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return basePodMapStruct.toDto(basePod);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BasePodDTO }
     * @return {@link List<BasePodDTO> }
     */
    @Override
    public List<BasePodDTO> selectSelective(BasePodDTO record) {
        BasePod basePod = basePodMapStruct.toEntity(record);

        List<BasePod> basePodList = basePodMapper.select(basePod);
        return basePodMapStruct.toDto(basePodList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BasePodDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BasePodDTO record) {
        BasePod basePod = basePodMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        basePod.setLastModifiedBy(userId);
        basePod.setLastModifiedTime(new Date());

        int num = basePodMapper.updateByPrimaryKey(basePod);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BasePodDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BasePodDTO record) {
        BasePod basePod = basePodMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        basePod.setLastModifiedBy(userId);
        basePod.setLastModifiedTime(new Date());

        int num = basePodMapper.updateByPrimaryKeySelective(basePod);
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
        int num = basePodMapper.deleteByPrimaryKey(id);
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
        return basePodMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return basePodMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return basePodMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BasePodDTO> }
     */
    @Override
    public GridReturnData<BasePodDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BasePodDTO> mGridReturnData = new GridReturnData<>();
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

        List<BasePod> list = basePodMapper.selectPage(map);

        PageInfo<BasePod> pageInfo = new PageInfo<>(list);
        PageInfo<BasePodDTO> pageInfoFinal = new PageInfo<>(basePodMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    @Override
    public Result showPodInfo(ShowPodInfoCondDTO request) {
        List<ShowPodInfoResultDTO> showPodInfoResultDTOList = basePodMapper.showPodInfo(request);
        showPodInfoResultDTOList.stream().forEach(showPodInfoResultDTO -> {
            String lockStatName = iBasePodDetailService.returnPodLockNameByResolveLockValue(showPodInfoResultDTO.getLockStat());
            showPodInfoResultDTO.setLockStatName(lockStatName);
        });
        return new Result(showPodInfoResultDTOList);
    }
}
