package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.LineBody;
import com.wisdom.iwcs.domain.task.dto.LineBodyDTO;
import com.wisdom.iwcs.mapper.task.LineBodyMapper;
import com.wisdom.iwcs.mapstruct.task.LineBodyMapStruct;
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
public class LineBodyService {
    private final Logger logger = LoggerFactory.getLogger(LineBodyService.class);

    private final LineBodyMapper lineBodyMapper;

    private final LineBodyMapStruct lineBodyMapStruct;

    @Autowired
    public LineBodyService(LineBodyMapStruct lineBodyMapStruct, LineBodyMapper lineBodyMapper) {
        this.lineBodyMapStruct = lineBodyMapStruct;
        this.lineBodyMapper = lineBodyMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link LineBodyDTO }
     *
     * @return int
     */
    public int insert(LineBodyDTO record) {
        LineBody lineBody = lineBodyMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = lineBodyMapper.insert(lineBody);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<LineBodyDTO> }
     *
     * @return int
     */
    public int insertBatch(List<LineBodyDTO> records) {
        List<LineBody> recordList = lineBodyMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = lineBodyMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link LineBodyDTO }
     */
    public LineBodyDTO selectByPrimaryKey(Integer id) {

        LineBody lineBody = lineBodyMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(lineBody, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return lineBodyMapStruct.toDto(lineBody);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link LineBodyDTO }
     *
     * @return {@link List<LineBodyDTO> }
     */
    public List<LineBodyDTO> selectSelective(LineBodyDTO record) {
        LineBody lineBody = lineBodyMapStruct.toEntity(record);

        List<LineBody> lineBodyList = lineBodyMapper.select(lineBody);
        return lineBodyMapStruct.toDto(lineBodyList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link LineBodyDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(LineBodyDTO record) {
        LineBody lineBody = lineBodyMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = lineBodyMapper.updateByPrimaryKey(lineBody);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link LineBodyDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(LineBodyDTO record) {
        LineBody lineBody = lineBodyMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = lineBodyMapper.updateByPrimaryKeySelective(lineBody);
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
        int num = lineBodyMapper.deleteByPrimaryKey(id);
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
//        return lineBodyMapper.deleteLogicByPrimaryKey(id);
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
        return lineBodyMapper.deleteByIds(String.join(",", ids));
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
//        return lineBodyMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<LineBodyDTO> }
     */
    public GridReturnData<LineBodyDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<LineBodyDTO> mGridReturnData = new GridReturnData<>();
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

        List<LineBody> list = lineBodyMapper.selectPage(map);

        PageInfo<LineBody> pageInfo = new PageInfo<>(list);
        PageInfo<LineBodyDTO> pageInfoFinal = new PageInfo<>(lineBodyMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
