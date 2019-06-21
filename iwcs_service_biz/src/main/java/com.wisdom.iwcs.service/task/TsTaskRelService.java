package com.wisdom.iwcs.service.task;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.TsTaskRel;
import com.wisdom.iwcs.domain.task.dto.TsTaskRelDTO;
import com.wisdom.iwcs.mapper.task.TsTaskRelMapper;
import com.wisdom.iwcs.mapstruct.task.TsTaskRelMapStruct;
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
public class TsTaskRelService {
    private final Logger logger = LoggerFactory.getLogger(TsTaskRelService.class);

    private final TsTaskRelMapper tsTaskRelMapper;

    private final TsTaskRelMapStruct tsTaskRelMapStruct;

    @Autowired
    public TsTaskRelService(TsTaskRelMapStruct tsTaskRelMapStruct, TsTaskRelMapper tsTaskRelMapper) {
        this.tsTaskRelMapStruct = tsTaskRelMapStruct;
        this.tsTaskRelMapper = tsTaskRelMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link TsTaskRelDTO }
     *
     * @return int
     */
    public int insert(TsTaskRelDTO record) {
        TsTaskRel tsTaskRel = tsTaskRelMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsTaskRelMapper.insert(tsTaskRel);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<TsTaskRelDTO> }
     *
     * @return int
     */
    public int insertBatch(List<TsTaskRelDTO> records) {
        List<TsTaskRel> recordList = tsTaskRelMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsTaskRelMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link TsTaskRelDTO }
     */
    public TsTaskRelDTO selectByPrimaryKey(Integer id) {

        TsTaskRel tsTaskRel = tsTaskRelMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(tsTaskRel, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return tsTaskRelMapStruct.toDto(tsTaskRel);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link TsTaskRelDTO }
     *
     * @return {@link List<TsTaskRelDTO> }
     */
    public List<TsTaskRelDTO> selectSelective(TsTaskRelDTO record) {
        TsTaskRel tsTaskRel = tsTaskRelMapStruct.toEntity(record);

        List<TsTaskRel> tsTaskRelList = tsTaskRelMapper.select(tsTaskRel);
        return tsTaskRelMapStruct.toDto(tsTaskRelList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link TsTaskRelDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(TsTaskRelDTO record) {
        TsTaskRel tsTaskRel = tsTaskRelMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsTaskRelMapper.updateByPrimaryKey(tsTaskRel);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link TsTaskRelDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(TsTaskRelDTO record) {
        TsTaskRel tsTaskRel = tsTaskRelMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = tsTaskRelMapper.updateByPrimaryKeySelective(tsTaskRel);
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
        int num = tsTaskRelMapper.deleteByPrimaryKey(id);
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
//        return tsTaskRelMapper.deleteLogicByPrimaryKey(id);
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
        return tsTaskRelMapper.deleteByIds(String.join(",", ids));
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
//        return tsTaskRelMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<TsTaskRelDTO> }
     */
    public GridReturnData<TsTaskRelDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<TsTaskRelDTO> mGridReturnData = new GridReturnData<>();
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

        List<TsTaskRel> list = tsTaskRelMapper.selectPage(map);

        PageInfo<TsTaskRel> pageInfo = new PageInfo<>(list);
        PageInfo<TsTaskRelDTO> pageInfoFinal = new PageInfo<>(tsTaskRelMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
