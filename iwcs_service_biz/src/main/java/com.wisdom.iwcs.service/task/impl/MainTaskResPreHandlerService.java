package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.domain.task.MainTaskResPreHandler;
import com.wisdom.iwcs.domain.task.dto.MainTaskResPreHandlerDTO;
import com.wisdom.iwcs.mapper.task.MainTaskResPreHandlerMapper;
import com.wisdom.iwcs.mapstruct.task.MainTaskResPreHandlerMapStruct;
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
public class MainTaskResPreHandlerService {
    private final Logger logger = LoggerFactory.getLogger(MainTaskResPreHandlerService.class);

    private final MainTaskResPreHandlerMapper mainTaskResPreHandlerMapper;

    private final MainTaskResPreHandlerMapStruct mainTaskResPreHandlerMapStruct;

    @Autowired
    public MainTaskResPreHandlerService(MainTaskResPreHandlerMapStruct mainTaskResPreHandlerMapStruct, MainTaskResPreHandlerMapper mainTaskResPreHandlerMapper) {
        this.mainTaskResPreHandlerMapStruct = mainTaskResPreHandlerMapStruct;
        this.mainTaskResPreHandlerMapper = mainTaskResPreHandlerMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link MainTaskResPreHandlerDTO }
     *
     * @return int
     */
    public int insert(MainTaskResPreHandlerDTO record) {
        MainTaskResPreHandler mainTaskResPreHandler = mainTaskResPreHandlerMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        mainTaskResPreHandler.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        mainTaskResPreHandler.setCreateTime(new Date());
        mainTaskResPreHandler.setLastModifiedTime(new Date());

        int num = mainTaskResPreHandlerMapper.insert(mainTaskResPreHandler);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<MainTaskResPreHandlerDTO> }
     *
     * @return int
     */
    public int insertBatch(List<MainTaskResPreHandlerDTO> records) {
        List<MainTaskResPreHandler> recordList = mainTaskResPreHandlerMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreateTime(new Date());
            record.setLastModifiedTime(new Date());
        });

        int num = mainTaskResPreHandlerMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link MainTaskResPreHandlerDTO }
     */
    public MainTaskResPreHandlerDTO selectByPrimaryKey(Integer id) {

        MainTaskResPreHandler mainTaskResPreHandler = mainTaskResPreHandlerMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(mainTaskResPreHandler, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return mainTaskResPreHandlerMapStruct.toDto(mainTaskResPreHandler);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link MainTaskResPreHandlerDTO }
     *
     * @return {@link List<MainTaskResPreHandlerDTO> }
     */
    public List<MainTaskResPreHandlerDTO> selectSelective(MainTaskResPreHandlerDTO record) {
        MainTaskResPreHandler mainTaskResPreHandler = mainTaskResPreHandlerMapStruct.toEntity(record);

        List<MainTaskResPreHandler> mainTaskResPreHandlerList = mainTaskResPreHandlerMapper.select(mainTaskResPreHandler);
        return mainTaskResPreHandlerMapStruct.toDto(mainTaskResPreHandlerList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link MainTaskResPreHandlerDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(MainTaskResPreHandlerDTO record) {
        MainTaskResPreHandler mainTaskResPreHandler = mainTaskResPreHandlerMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        mainTaskResPreHandler.setLastModifiedTime(new Date());

        int num = mainTaskResPreHandlerMapper.updateByPrimaryKey(mainTaskResPreHandler);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link MainTaskResPreHandlerDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(MainTaskResPreHandlerDTO record) {
        MainTaskResPreHandler mainTaskResPreHandler = mainTaskResPreHandlerMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        mainTaskResPreHandler.setLastModifiedTime(new Date());

        int num = mainTaskResPreHandlerMapper.updateByPrimaryKeySelective(mainTaskResPreHandler);
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
        int num = mainTaskResPreHandlerMapper.deleteByPrimaryKey(id);
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
    public int deleteLogicByPrimaryKey(Integer id) {
        return mainTaskResPreHandlerMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
    public int deleteMore(List<String> ids){
        return mainTaskResPreHandlerMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
    public int deleteMoreLogic(List<String> ids){
        return mainTaskResPreHandlerMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<MainTaskResPreHandlerDTO> }
     */
    public GridReturnData<MainTaskResPreHandlerDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<MainTaskResPreHandlerDTO> mGridReturnData = new GridReturnData<>();
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

        List<MainTaskResPreHandler> list = mainTaskResPreHandlerMapper.selectPage(map);

        PageInfo<MainTaskResPreHandler> pageInfo = new PageInfo<>(list);
        PageInfo<MainTaskResPreHandlerDTO> pageInfoFinal = new PageInfo<>(mainTaskResPreHandlerMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
