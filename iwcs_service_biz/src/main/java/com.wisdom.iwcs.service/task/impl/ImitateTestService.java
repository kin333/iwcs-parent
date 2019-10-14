package com.wisdom.iwcs.service.task.impl;

import com.wisdom.iwcs.domain.task.Imitatetest;
import com.wisdom.iwcs.domain.task.dto.ImitateTestDTO;
import com.wisdom.iwcs.mapstruct.task.ImitateTestMapStruct;

import com.wisdom.iwcs.mapper.task.ImitateTestMapper;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class ImitateTestService {
    private final Logger logger = LoggerFactory.getLogger(ImitateTestService.class);

    private final ImitateTestMapper ImitateTestMapper;

    private final ImitateTestMapStruct ImitateTestMapStruct;

    @Autowired
    public ImitateTestService(ImitateTestMapStruct ImitateTestMapStruct, ImitateTestMapper ImitateTestMapper) {
        this.ImitateTestMapStruct = ImitateTestMapStruct;
        this.ImitateTestMapper = ImitateTestMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link ImitateTestDTO }
     *
     * @return int
     */
    public int insert(ImitateTestDTO record) {
        Imitatetest imitateTest = ImitateTestMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = ImitateTestMapper.insert(imitateTest);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List< ImitateTestDTO > }
     *
     * @return int
     */
    public int insertBatch(List<ImitateTestDTO> records) {
        List<Imitatetest> recordList = ImitateTestMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = ImitateTestMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link ImitateTestDTO }
     */
    public ImitateTestDTO selectByPrimaryKey(Integer id) {

        Imitatetest imitateTest = ImitateTestMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(imitateTest, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return ImitateTestMapStruct.toDto(imitateTest);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link ImitateTestDTO }
     *
     * @return {@link List< ImitateTestDTO > }
     */
    public List<ImitateTestDTO> selectSelective(ImitateTestDTO record) {
        Imitatetest imitateTest = ImitateTestMapStruct.toEntity(record);

        List<Imitatetest> imitateTestList = ImitateTestMapper.select(imitateTest);
        return ImitateTestMapStruct.toDto(imitateTestList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link ImitateTestDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(ImitateTestDTO record) {
        Imitatetest imitateTest = ImitateTestMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = ImitateTestMapper.updateByPrimaryKey(imitateTest);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link ImitateTestDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(ImitateTestDTO record) {
        Imitatetest imitateTest = ImitateTestMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = ImitateTestMapper.updateByPrimaryKeySelective(imitateTest);
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
        int num = ImitateTestMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
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
        return ImitateTestMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData< ImitateTestDTO > }
     */
    public GridReturnData<ImitateTestDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<ImitateTestDTO> mGridReturnData = new GridReturnData<>();
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

        List<Imitatetest> list = ImitateTestMapper.selectPage(map);

        PageInfo<Imitatetest> pageInfo = new PageInfo<>(list);
        PageInfo<ImitateTestDTO> pageInfoFinal = new PageInfo<>(ImitateTestMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
