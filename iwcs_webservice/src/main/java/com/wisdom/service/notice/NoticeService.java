package com.wisdom.service.notice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.notice.NoticeMapStruct;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.notice.Notice;
import com.wisdom.iwcs.domain.notice.dto.NoticeDto;
import com.wisdom.iwcs.mapper.notice.NoticeMapper;
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
public class NoticeService {
    private final Logger logger = LoggerFactory.getLogger(NoticeService.class);

    @Autowired
    NoticeMapper noticeMapper;

    @Autowired
    NoticeMapStruct noticeMapStruct;

    /**
     * 写入记录
     *
     * @param NoticeDto record
     * @return int
     */
    public int insert(NoticeDto record) {
        Notice notice = noticeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        notice.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        Date currentDate = new Date();
        notice.setCreatedTime(currentDate);
        notice.setCreatedBy(userId);

        int num = noticeMapper.insert(notice);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param List<NoticeDto> records
     * @return int
     */
    public int insertBatch(List<NoticeDto> records) {
        List<Notice> recordList = noticeMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        Date currentDate = new Date();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(currentDate);
            record.setCreatedBy(userId);

        });

        int num = noticeMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param Integer id
     * @return NoticeDto
     */
    public NoticeDto selectByPrimaryKey(Integer id) {

        Notice notice = noticeMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(notice, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return noticeMapStruct.toDto(notice);
    }

    /**
     * 根据字段选择性查询
     *
     * @param NoticeDto record
     * @return List<NoticeDto>
     */
    public List<NoticeDto> selectSelective(NoticeDto record) {
        Notice notice = noticeMapStruct.toEntity(record);

        List<Notice> noticeList = noticeMapper.select(notice);
        return noticeMapStruct.toDto(noticeList);
    }

    /**
     * 根据主键更新
     *
     * @param NoticeDto record
     * @return int
     */
    public int updateByPrimaryKey(NoticeDto record) {
        Notice notice = noticeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        Date currentDate = new Date();


        int num = noticeMapper.updateByPrimaryKey(notice);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param NoticeDto record
     * @return int
     */
    public int updateByPrimaryKeySelective(NoticeDto record) {
        Notice notice = noticeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        Date currentDate = new Date();


        int num = noticeMapper.updateByPrimaryKeySelective(notice);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @param Integer id
     * @return int
     */
    public int deleteByPrimaryKey(Integer id) {
        int num = noticeMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键逻辑删除记录
     *
     * @param Integer id
     * @return int
     */
    public int deleteLogicByPrimaryKey(Integer id) {
        return noticeMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return noticeMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMoreLogic(List<String> ids) {
        return noticeMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param GridPageRequest 　gridPageRequest
     * @return　GridReturnData<NoticeDto>
     */
    public GridReturnData<NoticeDto> selectPage(GridPageRequest gridPageRequest) {
        Integer user_id = SecurityUtils.getCurrentUserId();

        GridReturnData<NoticeDto> mGridReturnData = new GridReturnData<>();
        List<GridFilterInfo> filterList = gridPageRequest.getFilterList();
        Map map = new HashMap();
        filterList.stream().forEach(gridFilterInfo -> {//封装筛选条件
            if (gridFilterInfo.getFilterKey() != null && gridFilterInfo.getFilterValue() != null) {
                map.put(gridFilterInfo.getFilterKey(), gridFilterInfo.getFilterValue());
            }
        });
        map.put("searchKey", gridPageRequest.getSearchKey());
        //对map中的参数的合法性进行校验

        String sortMyBatisByString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMyBatisByString);

        List<Notice> list = noticeMapper.selectPage(map);

        PageInfo<NoticeDto> pageInfo = new PageInfo<>(noticeMapStruct.toDto(list));
        mGridReturnData.setPageInfo(pageInfo);

        return mGridReturnData;
    }
}
