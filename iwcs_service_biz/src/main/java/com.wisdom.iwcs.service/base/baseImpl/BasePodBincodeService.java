package com.wisdom.iwcs.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BasePodBincode;
import com.wisdom.iwcs.domain.base.dto.BasePodBincodeDTO;
import com.wisdom.iwcs.mapper.base.BasePodBincodeMapper;
import com.wisdom.iwcs.mapstruct.base.BasePodBincodeMapStruct;
import com.wisdom.iwcs.service.base.IBasePodBincodeService;
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
public class BasePodBincodeService implements IBasePodBincodeService {
    private final Logger logger = LoggerFactory.getLogger(BasePodBincodeService.class);

    private final BasePodBincodeMapper basePodBincodeMapper;

    private final BasePodBincodeMapStruct basePodBincodeMapStruct;

    @Autowired
    public BasePodBincodeService(BasePodBincodeMapStruct basePodBincodeMapStruct, BasePodBincodeMapper basePodBincodeMapper) {
        this.basePodBincodeMapStruct = basePodBincodeMapStruct;
        this.basePodBincodeMapper = basePodBincodeMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BasePodBincodeDTO }
     * @return int
     */
    @Override
    public int insert(BasePodBincodeDTO record) {
        BasePodBincode basePodBincode = basePodBincodeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        basePodBincode.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        basePodBincode.setCreatedTime(new Date());
        basePodBincode.setCreatedBy(userId);
        basePodBincode.setLastModifiedBy(userId);
        basePodBincode.setLastModifiedTime(new Date());

        int num = basePodBincodeMapper.insert(basePodBincode);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BasePodBincodeDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BasePodBincodeDTO> records) {
        List<BasePodBincode> recordList = basePodBincodeMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = basePodBincodeMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BasePodBincodeDTO }
     */
    @Override
    public BasePodBincodeDTO selectByPrimaryKey(Integer id) {

        BasePodBincode basePodBincode = basePodBincodeMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(basePodBincode, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return basePodBincodeMapStruct.toDto(basePodBincode);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BasePodBincodeDTO }
     * @return {@link List<BasePodBincodeDTO> }
     */
    @Override
    public List<BasePodBincodeDTO> selectSelective(BasePodBincodeDTO record) {
        BasePodBincode basePodBincode = basePodBincodeMapStruct.toEntity(record);

        List<BasePodBincode> basePodBincodeList = basePodBincodeMapper.select(basePodBincode);
        return basePodBincodeMapStruct.toDto(basePodBincodeList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BasePodBincodeDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BasePodBincodeDTO record) {
        BasePodBincode basePodBincode = basePodBincodeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        basePodBincode.setLastModifiedBy(userId);
        basePodBincode.setLastModifiedTime(new Date());

        int num = basePodBincodeMapper.updateByPrimaryKey(basePodBincode);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BasePodBincodeDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BasePodBincodeDTO record) {
        BasePodBincode basePodBincode = basePodBincodeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        basePodBincode.setLastModifiedBy(userId);
        basePodBincode.setLastModifiedTime(new Date());

        int num = basePodBincodeMapper.updateByPrimaryKeySelective(basePodBincode);
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
        int num = basePodBincodeMapper.deleteByPrimaryKey(id);
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
        return basePodBincodeMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return basePodBincodeMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return basePodBincodeMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BasePodBincodeDTO> }
     */
    @Override
    public GridReturnData<BasePodBincodeDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BasePodBincodeDTO> mGridReturnData = new GridReturnData<>();
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

        List<BasePodBincode> list = basePodBincodeMapper.selectPage(map);

        PageInfo<BasePodBincode> pageInfo = new PageInfo<>(list);
        PageInfo<BasePodBincodeDTO> pageInfoFinal = new PageInfo<>(basePodBincodeMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
