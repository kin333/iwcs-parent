package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.domain.task.BaseMsgRcv;
import com.wisdom.iwcs.domain.task.dto.BaseMsgRcvDTO;
import com.wisdom.iwcs.mapper.task.BaseMsgRcvMapper;
import com.wisdom.iwcs.mapstruct.task.BaseMsgRcvMapStruct;
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

import com.wisdom.iwcs.common.utils.exception.Preconditions;

@Service
@Transactional(rollbackFor = Exception.class)
public class BaseMsgRcvService {
    private final Logger logger = LoggerFactory.getLogger(BaseMsgRcvService.class);

    private final BaseMsgRcvMapper baseMsgRcvMapper;

    private final BaseMsgRcvMapStruct baseMsgRcvMapStruct;

    @Autowired
    public BaseMsgRcvService(BaseMsgRcvMapStruct baseMsgRcvMapStruct, BaseMsgRcvMapper baseMsgRcvMapper) {
        this.baseMsgRcvMapStruct = baseMsgRcvMapStruct;
        this.baseMsgRcvMapper = baseMsgRcvMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link BaseMsgRcvDTO }
     *
     * @return int
     */
    public int insert(BaseMsgRcvDTO record) {
        BaseMsgRcv baseMsgRcv = baseMsgRcvMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMsgRcv.setCreatedTime(new Date());
        baseMsgRcv.setLastModifiedTime(new Date());

        int num = baseMsgRcvMapper.insert(baseMsgRcv);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<BaseMsgRcvDTO> }
     *
     * @return int
     */
    public int insertBatch(List<BaseMsgRcvDTO> records) {
        List<BaseMsgRcv> recordList = baseMsgRcvMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
            record.setLastModifiedTime(new Date());
        });

        int num = baseMsgRcvMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link BaseMsgRcvDTO }
     */
    public BaseMsgRcvDTO selectByPrimaryKey(Integer id) {

        BaseMsgRcv baseMsgRcv = baseMsgRcvMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseMsgRcv, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseMsgRcvMapStruct.toDto(baseMsgRcv);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link BaseMsgRcvDTO }
     *
     * @return {@link List<BaseMsgRcvDTO> }
     */
    public List<BaseMsgRcvDTO> selectSelective(BaseMsgRcvDTO record) {
        BaseMsgRcv baseMsgRcv = baseMsgRcvMapStruct.toEntity(record);

        List<BaseMsgRcv> baseMsgRcvList = baseMsgRcvMapper.select(baseMsgRcv);
        return baseMsgRcvMapStruct.toDto(baseMsgRcvList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link BaseMsgRcvDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(BaseMsgRcvDTO record) {
        BaseMsgRcv baseMsgRcv = baseMsgRcvMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMsgRcv.setLastModifiedTime(new Date());

        int num = baseMsgRcvMapper.updateByPrimaryKey(baseMsgRcv);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link BaseMsgRcvDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(BaseMsgRcvDTO record) {
        BaseMsgRcv baseMsgRcv = baseMsgRcvMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMsgRcv.setLastModifiedTime(new Date());

        int num = baseMsgRcvMapper.updateByPrimaryKeySelective(baseMsgRcv);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     *
     * @param id {@link Integer }
     *
     * @return int
     */
    public int deleteByPrimaryKey(Integer id) {
        int num = baseMsgRcvMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键逻辑删除记录
     *
     *
     * @param id {@link Integer }
     *
     * @return int
     */
//    public int deleteLogicByPrimaryKey(Integer id) {
//        return baseMsgRcvMapper.deleteLogicByPrimaryKey(id);
//    }

    /**
     * 根据主键删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
    public int deleteMore(List<String> ids){
        return baseMsgRcvMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
//    public int deleteMoreLogic(List<String> ids){
//        return baseMsgRcvMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<BaseMsgRcvDTO> }
     */
    public GridReturnData<BaseMsgRcvDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<BaseMsgRcvDTO> mGridReturnData = new GridReturnData<>();
        List<GridFilterInfo> filterList = gridPageRequest.getFilterList();
        Map<String, Object> map = new HashMap<>(2);
        filterList.forEach(gridFilterInfo -> {
            if(gridFilterInfo.getFilterKey() != null && gridFilterInfo.getFilterValue() != null){
                map.put(gridFilterInfo.getFilterKey(), gridFilterInfo.getFilterValue());
            }
        });
        map.put("searchKey", gridPageRequest.getSearchKey());
        // 对map中的参数的合法性进行校验

        String sortMyBatisByString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMyBatisByString);

        List<BaseMsgRcv> list = baseMsgRcvMapper.selectPage(map);

        PageInfo<BaseMsgRcv> pageInfo = new PageInfo<>(list);
        PageInfo<BaseMsgRcvDTO> pageInfoFinal = new PageInfo<>(baseMsgRcvMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
