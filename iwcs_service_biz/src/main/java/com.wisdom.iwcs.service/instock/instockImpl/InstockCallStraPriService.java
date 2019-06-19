package com.wisdom.iwcs.service.instock.instockImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.instock.InstockCallStraPri;
import com.wisdom.iwcs.domain.instock.dto.InstockCallStraPriDTO;
import com.wisdom.iwcs.mapper.instock.InstockCallStraPriMapper;
import com.wisdom.iwcs.mapstruct.instock.InstockCallStraPriMapStruct;
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
public class InstockCallStraPriService {
    private final Logger logger = LoggerFactory.getLogger(InstockCallStraPriService.class);

    private final InstockCallStraPriMapper instockCallStraPriMapper;

    private final InstockCallStraPriMapStruct instockCallStraPriMapStruct;

    @Autowired
    public InstockCallStraPriService(InstockCallStraPriMapStruct instockCallStraPriMapStruct, InstockCallStraPriMapper instockCallStraPriMapper) {
        this.instockCallStraPriMapStruct = instockCallStraPriMapStruct;
        this.instockCallStraPriMapper = instockCallStraPriMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link InstockCallStraPriDTO }
     * @return int
     */
    public int insert(InstockCallStraPriDTO record) {
        InstockCallStraPri instockCallStraPri = instockCallStraPriMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        instockCallStraPri.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        instockCallStraPri.setCreatedTime(new Date());
        instockCallStraPri.setCreatedBy(userId);
        instockCallStraPri.setLastModifiedBy(userId);
        instockCallStraPri.setLastModifiedTime(new Date());

        int num = instockCallStraPriMapper.insert(instockCallStraPri);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<InstockCallStraPriDTO> }
     * @return int
     */
    public int insertBatch(List<InstockCallStraPriDTO> records) {
        List<InstockCallStraPri> recordList = instockCallStraPriMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = instockCallStraPriMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link InstockCallStraPriDTO }
     */
    public InstockCallStraPriDTO selectByPrimaryKey(Integer id) {

        InstockCallStraPri instockCallStraPri = instockCallStraPriMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(instockCallStraPri, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return instockCallStraPriMapStruct.toDto(instockCallStraPri);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link InstockCallStraPriDTO }
     * @return {@link List<InstockCallStraPriDTO> }
     */
    public List<InstockCallStraPriDTO> selectSelective(InstockCallStraPriDTO record) {
        InstockCallStraPri instockCallStraPri = instockCallStraPriMapStruct.toEntity(record);

        List<InstockCallStraPri> instockCallStraPriList = instockCallStraPriMapper.select(instockCallStraPri);
        return instockCallStraPriMapStruct.toDto(instockCallStraPriList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link InstockCallStraPriDTO }
     * @return int
     */
    public int updateByPrimaryKey(InstockCallStraPriDTO record) {
        InstockCallStraPri instockCallStraPri = instockCallStraPriMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        instockCallStraPri.setLastModifiedBy(userId);
        instockCallStraPri.setLastModifiedTime(new Date());

        int num = instockCallStraPriMapper.updateByPrimaryKey(instockCallStraPri);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link InstockCallStraPriDTO }
     * @return int
     */
    public int updateByPrimaryKeySelective(InstockCallStraPriDTO record) {
        InstockCallStraPri instockCallStraPri = instockCallStraPriMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        instockCallStraPri.setLastModifiedBy(userId);
        instockCallStraPri.setLastModifiedTime(new Date());

        int num = instockCallStraPriMapper.updateByPrimaryKeySelective(instockCallStraPri);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    public int deleteByPrimaryKey(Integer id) {
        int num = instockCallStraPriMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键逻辑删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    public int deleteLogicByPrimaryKey(Integer id) {
        return instockCallStraPriMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return instockCallStraPriMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    public int deleteMoreLogic(List<String> ids) {
        return instockCallStraPriMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<InstockCallStraPriDTO> }
     */
    public GridReturnData<InstockCallStraPriDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<InstockCallStraPriDTO> mGridReturnData = new GridReturnData<>();
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

        List<InstockCallStraPri> list = instockCallStraPriMapper.selectPage(map);

        PageInfo<InstockCallStraPri> pageInfo = new PageInfo<>(list);
        PageInfo<InstockCallStraPriDTO> pageInfoFinal = new PageInfo<>(instockCallStraPriMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
