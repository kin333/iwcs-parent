package com.wisdom.iwcs.service.task.impl;

import com.wisdom.iwcs.mapstruct.task.imitateTestMapStruct;
import com.wisdom.iwcs.domain.task.imitateTest;
import com.wisdom.iwcs.domain.task.dto.imitateTestDTO;
import com.wisdom.iwcs.mapper.task.imitateTestMapper;
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
public class imitateTestService {
    private final Logger logger = LoggerFactory.getLogger(imitateTestService.class);

    private final imitateTestMapper imitateTestMapper;

    private final imitateTestMapStruct imitateTestMapStruct;

    @Autowired
    public imitateTestService(imitateTestMapStruct imitateTestMapStruct, imitateTestMapper imitateTestMapper) {
        this.imitateTestMapStruct = imitateTestMapStruct;
        this.imitateTestMapper = imitateTestMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link imitateTestDTO }
     *
     * @return int
     */
    public int insert(imitateTestDTO record) {
        imitateTest imitateTest = imitateTestMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = imitateTestMapper.insert(imitateTest);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<imitateTestDTO> }
     *
     * @return int
     */
    public int insertBatch(List<imitateTestDTO> records) {
        List<imitateTest> recordList = imitateTestMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = imitateTestMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link imitateTestDTO }
     */
    public imitateTestDTO selectByPrimaryKey(Integer id) {

        imitateTest imitateTest = imitateTestMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(imitateTest, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return imitateTestMapStruct.toDto(imitateTest);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link imitateTestDTO }
     *
     * @return {@link List<imitateTestDTO> }
     */
    public List<imitateTestDTO> selectSelective(imitateTestDTO record) {
        imitateTest imitateTest = imitateTestMapStruct.toEntity(record);

        List<imitateTest> imitateTestList = imitateTestMapper.select(imitateTest);
        return imitateTestMapStruct.toDto(imitateTestList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link imitateTestDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(imitateTestDTO record) {
        imitateTest imitateTest = imitateTestMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = imitateTestMapper.updateByPrimaryKey(imitateTest);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link imitateTestDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(imitateTestDTO record) {
        imitateTest imitateTest = imitateTestMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = imitateTestMapper.updateByPrimaryKeySelective(imitateTest);
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
        int num = imitateTestMapper.deleteByPrimaryKey(id);
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
        return imitateTestMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<imitateTestDTO> }
     */
    public GridReturnData<imitateTestDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<imitateTestDTO> mGridReturnData = new GridReturnData<>();
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

        List<imitateTest> list = imitateTestMapper.selectPage(map);

        PageInfo<imitateTest> pageInfo = new PageInfo<>(list);
        PageInfo<imitateTestDTO> pageInfoFinal = new PageInfo<>(imitateTestMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
