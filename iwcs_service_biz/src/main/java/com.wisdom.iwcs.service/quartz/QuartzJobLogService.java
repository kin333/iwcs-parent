package com.wisdom.iwcs.service.quartz;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.quartz.QuartzJobLog;
import com.wisdom.iwcs.domain.quartz.dto.QuartzJobLogDTO;
import com.wisdom.iwcs.mapper.quartz.QuartzJobLogMapper;
import com.wisdom.iwcs.mapstruct.quartz.QuartzJobLogMapStruct;
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
public class QuartzJobLogService {
    private final Logger logger = LoggerFactory.getLogger(QuartzJobLogService.class);

    private final QuartzJobLogMapper quartzJobLogMapper;

    private final QuartzJobLogMapStruct quartzJobLogMapStruct;

    @Autowired
    public QuartzJobLogService(QuartzJobLogMapStruct quartzJobLogMapStruct, QuartzJobLogMapper quartzJobLogMapper) {
        this.quartzJobLogMapStruct = quartzJobLogMapStruct;
        this.quartzJobLogMapper = quartzJobLogMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link QuartzJobLogDTO }
     * @return int
     */
    public int insert(QuartzJobLogDTO record) {
        QuartzJobLog quartzJobLog = quartzJobLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        quartzJobLog.setGmtCreate(new Date());

        int num = quartzJobLogMapper.insert(quartzJobLog);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<QuartzJobLogDTO> }
     * @return int
     */
    public int insertBatch(List<QuartzJobLogDTO> records) {
        List<QuartzJobLog> recordList = quartzJobLogMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setGmtCreate(new Date());

        });

        int num = quartzJobLogMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link QuartzJobLogDTO }
     */
    public QuartzJobLogDTO selectByPrimaryKey(Integer id) {

        QuartzJobLog quartzJobLog = quartzJobLogMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(quartzJobLog, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return quartzJobLogMapStruct.toDto(quartzJobLog);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link QuartzJobLogDTO }
     * @return {@link List<QuartzJobLogDTO> }
     */
    public List<QuartzJobLogDTO> selectSelective(QuartzJobLogDTO record) {
        QuartzJobLog quartzJobLog = quartzJobLogMapStruct.toEntity(record);

        List<QuartzJobLog> quartzJobLogList = quartzJobLogMapper.select(quartzJobLog);
        return quartzJobLogMapStruct.toDto(quartzJobLogList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link QuartzJobLogDTO }
     * @return int
     */
    public int updateByPrimaryKey(QuartzJobLogDTO record) {
        QuartzJobLog quartzJobLog = quartzJobLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();


        int num = quartzJobLogMapper.updateByPrimaryKey(quartzJobLog);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link QuartzJobLogDTO }
     * @return int
     */
    public int updateByPrimaryKeySelective(QuartzJobLogDTO record) {
        QuartzJobLog quartzJobLog = quartzJobLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();


        int num = quartzJobLogMapper.updateByPrimaryKeySelective(quartzJobLog);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    public int deleteByPrimaryKey(Integer id) {
        int num = quartzJobLogMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }


    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return quartzJobLogMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<QuartzJobLogDTO> }
     */
    public GridReturnData<QuartzJobLogDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<QuartzJobLogDTO> mGridReturnData = new GridReturnData<>();
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

        List<QuartzJobLog> list = quartzJobLogMapper.selectPage(map);

        PageInfo<QuartzJobLog> pageInfo = new PageInfo<>(list);
        PageInfo<QuartzJobLogDTO> pageInfoFinal = new PageInfo<>(quartzJobLogMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
