package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.WbTaskDetail;
import com.wisdom.iwcs.domain.task.dto.WbTaskDetailDTO;
import com.wisdom.iwcs.mapper.task.WbTaskDetailMapper;
import com.wisdom.iwcs.mapstruct.task.WbTaskDetailMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.task.IWbTaskDetailService;
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
public class WbTaskDetailService implements IWbTaskDetailService {
    private final Logger logger = LoggerFactory.getLogger(WbTaskDetailService.class);

    private final WbTaskDetailMapper wbTaskDetailMapper;

    private final WbTaskDetailMapStruct wbTaskDetailMapStruct;

    @Autowired
    public WbTaskDetailService(WbTaskDetailMapStruct wbTaskDetailMapStruct, WbTaskDetailMapper wbTaskDetailMapper) {
        this.wbTaskDetailMapStruct = wbTaskDetailMapStruct;
        this.wbTaskDetailMapper = wbTaskDetailMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link WbTaskDetailDTO }
     * @return int
     */
    @Override
    public int insert(WbTaskDetailDTO record) {
        WbTaskDetail wbTaskDetail = wbTaskDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        wbTaskDetail.setCreatedTime(new Date());
        wbTaskDetail.setCreatedBy(userId);
        wbTaskDetail.setLastModifiedBy(userId);
        wbTaskDetail.setLastModifiedTime(new Date());

        int num = wbTaskDetailMapper.insert(wbTaskDetail);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<WbTaskDetailDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<WbTaskDetailDTO> records) {
        List<WbTaskDetail> recordList = wbTaskDetailMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = wbTaskDetailMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link WbTaskDetailDTO }
     */
    @Override
    public WbTaskDetailDTO selectByPrimaryKey(Integer id) {

        WbTaskDetail wbTaskDetail = wbTaskDetailMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(wbTaskDetail, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return wbTaskDetailMapStruct.toDto(wbTaskDetail);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link WbTaskDetailDTO }
     * @return {@link List<WbTaskDetailDTO> }
     */
    @Override
    public List<WbTaskDetailDTO> selectSelective(WbTaskDetailDTO record) {
        WbTaskDetail wbTaskDetail = wbTaskDetailMapStruct.toEntity(record);

        List<WbTaskDetail> wbTaskDetailList = wbTaskDetailMapper.select(wbTaskDetail);
        return wbTaskDetailMapStruct.toDto(wbTaskDetailList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link WbTaskDetailDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(WbTaskDetailDTO record) {
        WbTaskDetail wbTaskDetail = wbTaskDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        wbTaskDetail.setLastModifiedBy(userId);
        wbTaskDetail.setLastModifiedTime(new Date());

        int num = wbTaskDetailMapper.updateByPrimaryKey(wbTaskDetail);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link WbTaskDetailDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(WbTaskDetailDTO record) {
        WbTaskDetail wbTaskDetail = wbTaskDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        wbTaskDetail.setLastModifiedBy(userId);
        wbTaskDetail.setLastModifiedTime(new Date());

        int num = wbTaskDetailMapper.updateByPrimaryKeySelective(wbTaskDetail);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        int num = wbTaskDetailMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }


    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return wbTaskDetailMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<WbTaskDetailDTO> }
     */
    @Override
    public GridReturnData<WbTaskDetailDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<WbTaskDetailDTO> mGridReturnData = new GridReturnData<>();
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

        List<WbTaskDetail> list = wbTaskDetailMapper.selectPage(map);

        PageInfo<WbTaskDetail> pageInfo = new PageInfo<>(list);
        PageInfo<WbTaskDetailDTO> pageInfoFinal = new PageInfo<>(wbTaskDetailMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 根据任务编号获取任务详情
     *
     * @param record
     * @return
     */
    @Override
    public List<WbTaskDetailDTO> selectAgvTaskDetail(WbTaskDetailDTO record) {
        //判断任务编号是否存在
        Preconditions.checkBusinessError(record.getWbTaskNo() == null || record.getWbTaskNo().isEmpty(), "缺少工作台任务编号");

        List<WbTaskDetailDTO> wbTaskDetailList = wbTaskDetailMapper.selectAgvTaskDetail(record);
        return wbTaskDetailList;
    }
}
