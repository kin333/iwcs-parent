package com.wisdom.iwcs.service.task;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.TsSubTaskTyp;
import com.wisdom.iwcs.domain.task.dto.TsSubTaskTypDTO;
import com.wisdom.iwcs.mapper.task.TsSubTaskTypMapper;
import com.wisdom.iwcs.mapstruct.task.TsSubTaskTypMapStruct;
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
public class TsSubTaskTypService {
    private final Logger logger = LoggerFactory.getLogger(TsSubTaskTypService.class);

    private final TsSubTaskTypMapper tsSubTaskTypMapper;

    private final TsSubTaskTypMapStruct tsSubTaskTypMapStruct;

    @Autowired
    public TsSubTaskTypService(TsSubTaskTypMapStruct tsSubTaskTypMapStruct, TsSubTaskTypMapper tsSubTaskTypMapper) {
        this.tsSubTaskTypMapStruct = tsSubTaskTypMapStruct;
        this.tsSubTaskTypMapper = tsSubTaskTypMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link TsSubTaskTypDTO }
     *
     * @return int
     */
    public int insert(TsSubTaskTypDTO record) {
        TsSubTaskTyp tsSubTaskTyp = tsSubTaskTypMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsSubTaskTypMapper.insert(tsSubTaskTyp);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<TsSubTaskTypDTO> }
     *
     * @return int
     */
    public int insertBatch(List<TsSubTaskTypDTO> records) {
        List<TsSubTaskTyp> recordList = tsSubTaskTypMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsSubTaskTypMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link TsSubTaskTypDTO }
     */
    public TsSubTaskTypDTO selectByPrimaryKey(Integer id) {

        TsSubTaskTyp tsSubTaskTyp = tsSubTaskTypMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(tsSubTaskTyp, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return tsSubTaskTypMapStruct.toDto(tsSubTaskTyp);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link TsSubTaskTypDTO }
     *
     * @return {@link List<TsSubTaskTypDTO> }
     */
    public List<TsSubTaskTypDTO> selectSelective(TsSubTaskTypDTO record) {
        TsSubTaskTyp tsSubTaskTyp = tsSubTaskTypMapStruct.toEntity(record);

        List<TsSubTaskTyp> tsSubTaskTypList = tsSubTaskTypMapper.select(tsSubTaskTyp);
        return tsSubTaskTypMapStruct.toDto(tsSubTaskTypList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link TsSubTaskTypDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(TsSubTaskTypDTO record) {
        TsSubTaskTyp tsSubTaskTyp = tsSubTaskTypMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsSubTaskTypMapper.updateByPrimaryKey(tsSubTaskTyp);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link TsSubTaskTypDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(TsSubTaskTypDTO record) {
        TsSubTaskTyp tsSubTaskTyp = tsSubTaskTypMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsSubTaskTypMapper.updateByPrimaryKeySelective(tsSubTaskTyp);
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
        int num = tsSubTaskTypMapper.deleteByPrimaryKey(id);
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
//        return tsSubTaskTypMapper.deleteLogicByPrimaryKey(id);
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
        return tsSubTaskTypMapper.deleteByIds(String.join(",", ids));
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
//        return tsSubTaskTypMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<TsSubTaskTypDTO> }
     */
    public GridReturnData<TsSubTaskTypDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<TsSubTaskTypDTO> mGridReturnData = new GridReturnData<>();
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

        List<TsSubTaskTyp> list = tsSubTaskTypMapper.selectPage(map);

        PageInfo<TsSubTaskTyp> pageInfo = new PageInfo<>(list);
        PageInfo<TsSubTaskTypDTO> pageInfoFinal = new PageInfo<>(tsSubTaskTypMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
