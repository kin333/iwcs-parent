package com.wisdom.service.instock.instockImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.instock.InstockCallStraCondParamMapStruct;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.instock.InstockCallStraCondParam;
import com.wisdom.iwcs.domain.instock.dto.InstockCallStraCondParamDTO;
import com.wisdom.iwcs.mapper.instock.InstockCallStraCondParamMapper;
import com.wisdom.security.SecurityUtils;
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
public class InstockCallStraCondParamService {
    private final Logger logger = LoggerFactory.getLogger(InstockCallStraCondParamService.class);

    private final InstockCallStraCondParamMapper instockCallStraCondParamMapper;

    private final InstockCallStraCondParamMapStruct instockCallStraCondParamMapStruct;

    @Autowired
    public InstockCallStraCondParamService(InstockCallStraCondParamMapStruct instockCallStraCondParamMapStruct, InstockCallStraCondParamMapper instockCallStraCondParamMapper) {
        this.instockCallStraCondParamMapStruct = instockCallStraCondParamMapStruct;
        this.instockCallStraCondParamMapper = instockCallStraCondParamMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link InstockCallStraCondParamDTO }
     * @return int
     */
    public int insert(InstockCallStraCondParamDTO record) {
        InstockCallStraCondParam instockCallStraCondParam = instockCallStraCondParamMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        instockCallStraCondParam.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        instockCallStraCondParam.setCreatedTime(new Date());
        instockCallStraCondParam.setCreatedBy(userId);
        instockCallStraCondParam.setLastModifiedBy(userId);
        instockCallStraCondParam.setLastModifiedTime(new Date());

        int num = instockCallStraCondParamMapper.insert(instockCallStraCondParam);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<InstockCallStraCondParamDTO> }
     * @return int
     */
    public int insertBatch(List<InstockCallStraCondParamDTO> records) {
        List<InstockCallStraCondParam> recordList = instockCallStraCondParamMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = instockCallStraCondParamMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link InstockCallStraCondParamDTO }
     */
    public InstockCallStraCondParamDTO selectByPrimaryKey(Integer id) {

        InstockCallStraCondParam instockCallStraCondParam = instockCallStraCondParamMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(instockCallStraCondParam, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return instockCallStraCondParamMapStruct.toDto(instockCallStraCondParam);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link InstockCallStraCondParamDTO }
     * @return {@link List<InstockCallStraCondParamDTO> }
     */
    public List<InstockCallStraCondParamDTO> selectSelective(InstockCallStraCondParamDTO record) {
        InstockCallStraCondParam instockCallStraCondParam = instockCallStraCondParamMapStruct.toEntity(record);

        List<InstockCallStraCondParam> instockCallStraCondParamList = instockCallStraCondParamMapper.select(instockCallStraCondParam);
        return instockCallStraCondParamMapStruct.toDto(instockCallStraCondParamList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link InstockCallStraCondParamDTO }
     * @return int
     */
    public int updateByPrimaryKey(InstockCallStraCondParamDTO record) {
        InstockCallStraCondParam instockCallStraCondParam = instockCallStraCondParamMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        instockCallStraCondParam.setLastModifiedBy(userId);
        instockCallStraCondParam.setLastModifiedTime(new Date());

        int num = instockCallStraCondParamMapper.updateByPrimaryKey(instockCallStraCondParam);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link InstockCallStraCondParamDTO }
     * @return int
     */
    public int updateByPrimaryKeySelective(InstockCallStraCondParamDTO record) {
        InstockCallStraCondParam instockCallStraCondParam = instockCallStraCondParamMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        instockCallStraCondParam.setLastModifiedBy(userId);
        instockCallStraCondParam.setLastModifiedTime(new Date());

        int num = instockCallStraCondParamMapper.updateByPrimaryKeySelective(instockCallStraCondParam);
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
        int num = instockCallStraCondParamMapper.deleteByPrimaryKey(id);
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
        return instockCallStraCondParamMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return instockCallStraCondParamMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    public int deleteMoreLogic(List<String> ids) {
        return instockCallStraCondParamMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<InstockCallStraCondParamDTO> }
     */
    public GridReturnData<InstockCallStraCondParamDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<InstockCallStraCondParamDTO> mGridReturnData = new GridReturnData<>();
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

        List<InstockCallStraCondParam> list = instockCallStraCondParamMapper.selectPage(map);

        PageInfo<InstockCallStraCondParam> pageInfo = new PageInfo<>(list);
        PageInfo<InstockCallStraCondParamDTO> pageInfoFinal = new PageInfo<>(instockCallStraCondParamMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
