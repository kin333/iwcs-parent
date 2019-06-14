package com.wisdom.service.instock.instockImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.instock.InstockCallStraMapStruct;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.instock.InstockCallStra;
import com.wisdom.iwcs.domain.instock.dto.InstockCallStraDTO;
import com.wisdom.iwcs.mapper.instock.InstockCallStraMapper;
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
public class InstockCallStraService {
    private final Logger logger = LoggerFactory.getLogger(InstockCallStraService.class);

    private final InstockCallStraMapper instockCallStraMapper;

    private final InstockCallStraMapStruct instockCallStraMapStruct;

    @Autowired
    public InstockCallStraService(InstockCallStraMapStruct instockCallStraMapStruct, InstockCallStraMapper instockCallStraMapper) {
        this.instockCallStraMapStruct = instockCallStraMapStruct;
        this.instockCallStraMapper = instockCallStraMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link InstockCallStraDTO }
     * @return int
     */
    public int insert(InstockCallStraDTO record) {
        InstockCallStra instockCallStra = instockCallStraMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        instockCallStra.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        instockCallStra.setCreatedTime(new Date());
        instockCallStra.setCreatedBy(userId);
        instockCallStra.setLastModifiedBy(userId);
        instockCallStra.setLastModifiedTime(new Date());

        int num = instockCallStraMapper.insert(instockCallStra);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<InstockCallStraDTO> }
     * @return int
     */
    public int insertBatch(List<InstockCallStraDTO> records) {
        List<InstockCallStra> recordList = instockCallStraMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = instockCallStraMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link InstockCallStraDTO }
     */
    public InstockCallStraDTO selectByPrimaryKey(Integer id) {

        InstockCallStra instockCallStra = instockCallStraMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(instockCallStra, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return instockCallStraMapStruct.toDto(instockCallStra);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link InstockCallStraDTO }
     * @return {@link List<InstockCallStraDTO> }
     */
    public List<InstockCallStraDTO> selectSelective(InstockCallStraDTO record) {
        InstockCallStra instockCallStra = instockCallStraMapStruct.toEntity(record);

        List<InstockCallStra> instockCallStraList = instockCallStraMapper.select(instockCallStra);
        return instockCallStraMapStruct.toDto(instockCallStraList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link InstockCallStraDTO }
     * @return int
     */
    public int updateByPrimaryKey(InstockCallStraDTO record) {
        InstockCallStra instockCallStra = instockCallStraMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        instockCallStra.setLastModifiedBy(userId);
        instockCallStra.setLastModifiedTime(new Date());

        int num = instockCallStraMapper.updateByPrimaryKey(instockCallStra);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link InstockCallStraDTO }
     * @return int
     */
    public int updateByPrimaryKeySelective(InstockCallStraDTO record) {
        InstockCallStra instockCallStra = instockCallStraMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        instockCallStra.setLastModifiedBy(userId);
        instockCallStra.setLastModifiedTime(new Date());

        int num = instockCallStraMapper.updateByPrimaryKeySelective(instockCallStra);
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
        int num = instockCallStraMapper.deleteByPrimaryKey(id);
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
        return instockCallStraMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return instockCallStraMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    public int deleteMoreLogic(List<String> ids) {
        return instockCallStraMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<InstockCallStraDTO> }
     */
    public GridReturnData<InstockCallStraDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<InstockCallStraDTO> mGridReturnData = new GridReturnData<>();
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

        List<InstockCallStra> list = instockCallStraMapper.selectPage(map);

        PageInfo<InstockCallStra> pageInfo = new PageInfo<>(list);
        PageInfo<InstockCallStraDTO> pageInfoFinal = new PageInfo<>(instockCallStraMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
