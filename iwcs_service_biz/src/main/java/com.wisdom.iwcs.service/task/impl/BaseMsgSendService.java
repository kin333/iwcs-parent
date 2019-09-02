package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.domain.task.BaseMsgSend;
import com.wisdom.iwcs.domain.task.dto.BaseMsgSendDTO;
import com.wisdom.iwcs.mapper.task.BaseMsgSendMapper;
import com.wisdom.iwcs.mapstruct.task.BaseMsgSendMapStruct;
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
public class BaseMsgSendService {
    private final Logger logger = LoggerFactory.getLogger(BaseMsgSendService.class);

    private final BaseMsgSendMapper baseMsgSendMapper;

    private final BaseMsgSendMapStruct baseMsgSendMapStruct;

    @Autowired
    public BaseMsgSendService(BaseMsgSendMapStruct baseMsgSendMapStruct, BaseMsgSendMapper baseMsgSendMapper) {
        this.baseMsgSendMapStruct = baseMsgSendMapStruct;
        this.baseMsgSendMapper = baseMsgSendMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link BaseMsgSendDTO }
     *
     * @return int
     */
    public int insert(BaseMsgSendDTO record) {
        BaseMsgSend baseMsgSend = baseMsgSendMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMsgSend.setCreatedTime(new Date());
        baseMsgSend.setLastModifiedTime(new Date());

        int num = baseMsgSendMapper.insert(baseMsgSend);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<BaseMsgSendDTO> }
     *
     * @return int
     */
    public int insertBatch(List<BaseMsgSendDTO> records) {
        List<BaseMsgSend> recordList = baseMsgSendMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
            record.setLastModifiedTime(new Date());
        });

        int num = baseMsgSendMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link BaseMsgSendDTO }
     */
    public BaseMsgSendDTO selectByPrimaryKey(Integer id) {

        BaseMsgSend baseMsgSend = baseMsgSendMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseMsgSend, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseMsgSendMapStruct.toDto(baseMsgSend);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link BaseMsgSendDTO }
     *
     * @return {@link List<BaseMsgSendDTO> }
     */
    public List<BaseMsgSendDTO> selectSelective(BaseMsgSendDTO record) {
        BaseMsgSend baseMsgSend = baseMsgSendMapStruct.toEntity(record);

        List<BaseMsgSend> baseMsgSendList = baseMsgSendMapper.select(baseMsgSend);
        return baseMsgSendMapStruct.toDto(baseMsgSendList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link BaseMsgSendDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(BaseMsgSendDTO record) {
        BaseMsgSend baseMsgSend = baseMsgSendMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMsgSend.setLastModifiedTime(new Date());

        int num = baseMsgSendMapper.updateByPrimaryKey(baseMsgSend);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link BaseMsgSendDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(BaseMsgSendDTO record) {
        BaseMsgSend baseMsgSend = baseMsgSendMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMsgSend.setLastModifiedTime(new Date());

        int num = baseMsgSendMapper.updateByPrimaryKeySelective(baseMsgSend);
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
        int num = baseMsgSendMapper.deleteByPrimaryKey(id);
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
//        return baseMsgSendMapper.deleteLogicByPrimaryKey(id);
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
        return baseMsgSendMapper.deleteByIds(String.join(",", ids));
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
//        return baseMsgSendMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<BaseMsgSendDTO> }
     */
    public GridReturnData<BaseMsgSendDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<BaseMsgSendDTO> mGridReturnData = new GridReturnData<>();
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

        List<BaseMsgSend> list = baseMsgSendMapper.selectPage(map);

        PageInfo<BaseMsgSend> pageInfo = new PageInfo<>(list);
        PageInfo<BaseMsgSendDTO> pageInfoFinal = new PageInfo<>(baseMsgSendMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
