package com.wisdom.service.quartz;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.quartz.QuartzJobMapStruct;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.quartz.QuartzJob;
import com.wisdom.iwcs.domain.quartz.dto.QuartzJobDTO;
import com.wisdom.iwcs.mapper.quartz.QuartzJobMapper;
import com.wisdom.security.SecurityUtils;
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
public class QuartzJobService {
    private final Logger logger = LoggerFactory.getLogger(QuartzJobService.class);

    private final QuartzJobMapper quartzJobMapper;

    private final QuartzJobMapStruct quartzJobMapStruct;

    @Autowired
    public QuartzJobService(QuartzJobMapStruct quartzJobMapStruct, QuartzJobMapper quartzJobMapper) {
        this.quartzJobMapStruct = quartzJobMapStruct;
        this.quartzJobMapper = quartzJobMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link QuartzJobDTO }
     * @return int
     */
    public int insert(QuartzJobDTO record) {
        QuartzJob quartzJob = quartzJobMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        quartzJob.setGmtCreate(new Date());
        quartzJob.setGmtModified(new Date());

        int num = quartzJobMapper.insert(quartzJob);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<QuartzJobDTO> }
     * @return int
     */
    public int insertBatch(List<QuartzJobDTO> records) {
        List<QuartzJob> recordList = quartzJobMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setGmtCreate(new Date());
            record.setGmtModified(new Date());

        });

        int num = quartzJobMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link QuartzJobDTO }
     */
    public QuartzJobDTO selectByPrimaryKey(Integer id) {

        QuartzJob quartzJob = quartzJobMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(quartzJob, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return quartzJobMapStruct.toDto(quartzJob);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link QuartzJobDTO }
     * @return {@link List<QuartzJobDTO> }
     */
    public List<QuartzJobDTO> selectSelective(QuartzJobDTO record) {
        QuartzJob quartzJob = quartzJobMapStruct.toEntity(record);

        List<QuartzJob> quartzJobList = quartzJobMapper.select(quartzJob);
        return quartzJobMapStruct.toDto(quartzJobList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link QuartzJobDTO }
     * @return int
     */
    public int updateByPrimaryKey(QuartzJobDTO record) {
        QuartzJob quartzJob = quartzJobMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        quartzJob.setGmtModified(new Date());

        int num = quartzJobMapper.updateByPrimaryKey(quartzJob);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link QuartzJobDTO }
     * @return int
     */
    public int updateByPrimaryKeySelective(QuartzJobDTO record) {
        QuartzJob quartzJob = quartzJobMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        quartzJob.setGmtModified(new Date());

        int num = quartzJobMapper.updateByPrimaryKeySelective(quartzJob);
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
        int num = quartzJobMapper.deleteByPrimaryKey(id);
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
        return quartzJobMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<QuartzJobDTO> }
     */
    public GridReturnData<QuartzJobDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<QuartzJobDTO> mGridReturnData = new GridReturnData<>();
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

        List<QuartzJob> list = quartzJobMapper.selectPage(map);

        PageInfo<QuartzJob> pageInfo = new PageInfo<>(list);
        PageInfo<QuartzJobDTO> pageInfoFinal = new PageInfo<>(quartzJobMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
