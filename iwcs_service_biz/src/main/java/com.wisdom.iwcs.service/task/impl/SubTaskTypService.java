package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.SubTaskTyp;
import com.wisdom.iwcs.domain.task.dto.SubTaskTypDTO;
import com.wisdom.iwcs.mapper.task.SubTaskTypMapper;
import com.wisdom.iwcs.mapstruct.task.SubTaskTypMapStruct;
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
public class SubTaskTypService {
    private final Logger logger = LoggerFactory.getLogger(SubTaskTypService.class);

    private final SubTaskTypMapper SubTaskTypMapper;

    private final SubTaskTypMapStruct SubTaskTypMapStruct;

    @Autowired
    public SubTaskTypService(SubTaskTypMapStruct SubTaskTypMapStruct, SubTaskTypMapper SubTaskTypMapper) {
        this.SubTaskTypMapStruct = SubTaskTypMapStruct;
        this.SubTaskTypMapper = SubTaskTypMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link SubTaskTypDTO }
     *
     * @return int
     */
    public int insert(SubTaskTypDTO record) {
        SubTaskTyp SubTaskTyp = SubTaskTypMapStruct.toEntity(record);

        SubTaskTyp subTaskTypList = SubTaskTypMapper.selectByTypeCode(SubTaskTyp.getSubTaskTypCode());

        if (subTaskTypList != null) {
            return 400;
        }

        Integer userId = SecurityUtils.getCurrentUserId();
        SubTaskTyp.setCreateDate(new Date());
        int num = SubTaskTypMapper.insert(SubTaskTyp);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<SubTaskTypDTO> }
     *
     * @return int
     */
    public int insertBatch(List<SubTaskTypDTO> records) {
        List<SubTaskTyp> recordList = SubTaskTypMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = SubTaskTypMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link SubTaskTypDTO }
     */
    public SubTaskTypDTO selectByPrimaryKey(Integer id) {

        SubTaskTyp SubTaskTyp = SubTaskTypMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(SubTaskTyp, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return SubTaskTypMapStruct.toDto(SubTaskTyp);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link SubTaskTypDTO }
     *
     * @return {@link List<SubTaskTypDTO> }
     */
    public List<SubTaskTypDTO> selectSelective(SubTaskTypDTO record) {
        SubTaskTyp SubTaskTyp = SubTaskTypMapStruct.toEntity(record);

        List<SubTaskTyp> SubTaskTypList = SubTaskTypMapper.select(SubTaskTyp);
        return SubTaskTypMapStruct.toDto(SubTaskTypList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link SubTaskTypDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(SubTaskTypDTO record) {
        SubTaskTyp SubTaskTyp = SubTaskTypMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = SubTaskTypMapper.updateByPrimaryKey(SubTaskTyp);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    public SubTaskTyp selectSubTaskTypeByCode(SubTaskTyp subTaskTyp) {
        SubTaskTyp subTaskType = SubTaskTypMapper.selectByTypeCode(subTaskTyp.getSubTaskTypCode());
        return  subTaskType;
    }
    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link SubTaskTypDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(SubTaskTypDTO record) {
        SubTaskTyp SubTaskTyp = SubTaskTypMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = SubTaskTypMapper.updateByPrimaryKeySelective(SubTaskTyp);
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
        int num = SubTaskTypMapper.deleteByPrimaryKey(id);
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
//        return SubTaskTypMapper.deleteLogicByPrimaryKey(id);
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
        return SubTaskTypMapper.deleteByIds(String.join(",", ids));
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
//        return SubTaskTypMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<SubTaskTypDTO> }
     */
    public GridReturnData<SubTaskTypDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<SubTaskTypDTO> mGridReturnData = new GridReturnData<>();
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

        List<SubTaskTyp> list = SubTaskTypMapper.selectPage(map);

        PageInfo<SubTaskTyp> pageInfo = new PageInfo<>(list);
        PageInfo<SubTaskTypDTO> pageInfoFinal = new PageInfo<>(SubTaskTypMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    public List<SubTaskTyp> selectAll() {

        List<SubTaskTyp> subTaskTypList = SubTaskTypMapper.selectSubTypeAll();

        return subTaskTypList;
    }
}
