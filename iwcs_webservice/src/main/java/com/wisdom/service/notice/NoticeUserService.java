package com.wisdom.service.notice;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.notice.NoticeUserMapStruct;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.notice.Notice;
import com.wisdom.iwcs.domain.notice.NoticeUser;
import com.wisdom.iwcs.domain.notice.dto.NoticeDto;
import com.wisdom.iwcs.domain.notice.dto.NoticeTypeDto;
import com.wisdom.iwcs.domain.notice.dto.NoticeUserDto;
import com.wisdom.iwcs.mapper.notice.NoticeMapper;
import com.wisdom.iwcs.mapper.notice.NoticeUserMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.message.NotificationService;
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
public class NoticeUserService {
    private final Logger logger = LoggerFactory.getLogger(NoticeUserService.class);

    @Autowired
    NoticeUserMapper noticeCustomerMapper;

    @Autowired
    NoticeMapper noticeMapper;

    @Autowired
    NoticeUserMapStruct noticeCustomerMapStruct;

    @Autowired
    NotificationService notificationService;

    /**
     * 写入记录
     *
     * @param NoticeCustomerDto record
     * @return int
     */
    public int insert(NoticeUserDto record) {
        NoticeUser noticeCustomer = noticeCustomerMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        noticeCustomer.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        Date currentDate = new Date();
        noticeCustomer.setCreatedTime(currentDate);

        int num = noticeCustomerMapper.insert(noticeCustomer);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param List<NoticeCustomerDto> records
     * @return int
     */
    public int insertBatch(List<NoticeUserDto> records) {
        List<NoticeUser> recordList = noticeCustomerMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        Date currentDate = new Date();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(currentDate);

        });

        int num = noticeCustomerMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param Integer id
     * @return NoticeCustomerDto
     */
    public NoticeUserDto selectByPrimaryKey(Integer id) {

        NoticeUser noticeCustomer = noticeCustomerMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(noticeCustomer, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return noticeCustomerMapStruct.toDto(noticeCustomer);
    }

    /**
     * 根据字段选择性查询
     *
     * @param NoticeCustomerDto record
     * @return List<NoticeCustomerDto>
     */
    public List<NoticeUserDto> selectSelective(NoticeUserDto record) {
        NoticeUser noticeCustomer = noticeCustomerMapStruct.toEntity(record);

        List<NoticeUser> noticeCustomerList = noticeCustomerMapper.select(noticeCustomer);
        return noticeCustomerMapStruct.toDto(noticeCustomerList);
    }

    /**
     * 根据主键更新
     *
     * @param NoticeCustomerDto record
     * @return int
     */
    public int updateByPrimaryKey(NoticeUserDto record) {
        NoticeUser noticeCustomer = noticeCustomerMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        Date currentDate = new Date();

        int num = noticeCustomerMapper.updateByPrimaryKey(noticeCustomer);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param NoticeCustomerDto record
     * @return int
     */
    public int updateByPrimaryKeySelective(NoticeUserDto record) {
        NoticeUser noticeCustomer = noticeCustomerMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        Date currentDate = new Date();

        int num = noticeCustomerMapper.updateByPrimaryKeySelective(noticeCustomer);
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
        int num = noticeCustomerMapper.deleteByPrimaryKey(id);
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
        return noticeCustomerMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return noticeCustomerMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMoreLogic(List<String> ids) {
        return noticeCustomerMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param GridPageRequest 　gridPageRequest
     * @return　GridReturnData<NoticeCustomerDto>
     */
    public GridReturnData<NoticeUserDto> selectPage(GridPageRequest gridPageRequest) {
        Integer user_id = SecurityUtils.getCurrentUserId();

        GridReturnData<NoticeUserDto> mGridReturnData = new GridReturnData<>();
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

        List<NoticeUser> list = noticeCustomerMapper.selectPage(map);

        PageInfo<NoticeUserDto> pageInfo = new PageInfo<>(noticeCustomerMapStruct.toDto(list));
        mGridReturnData.setPageInfo(pageInfo);

        return mGridReturnData;
    }

    public JSONObject getUnReadNoticeNumByUserId(Integer id) {
        JSONObject obj = new JSONObject();
        Integer companyId = SecurityUtils.getCurrentCompanyId();
        Map map = new HashMap();
        map.put("id", id);
        map.put("companyId", companyId);

        List<NoticeUser> list = noticeCustomerMapper.getUnReadNoticeNumByUserId(map);
        for (NoticeUser noticeCustomer : list) {
            if (noticeCustomer.getStatus() == 0) {
                obj.put("unRead", noticeCustomer.getReceiveId());
            }
            if (noticeCustomer.getStatus() == 1) {
                obj.put("read", noticeCustomer.getReceiveId());
            }
            if (noticeCustomer.getStatus() == 2) {
                obj.put("recycle", noticeCustomer.getReceiveId());
            }
            if (list.size() == 2) {
                obj.put("recycle", 0);
            }
        }
        return obj;
    }

    public NoticeTypeDto getNoticePageByType(Integer id) {
        NoticeTypeDto ntd = new NoticeTypeDto();
        Integer companyId = SecurityUtils.getCurrentCompanyId();
        Map map = new HashMap();
        map.put("id", id);
        map.put("companyId", companyId);
        map.put("status", "0");
        List<NoticeDto> unreadList = noticeCustomerMapper.getNoticeByStatus(map);
        map.put("status", "1");
        List<NoticeDto> readList = noticeCustomerMapper.getNoticeByStatus(map);
        map.put("status", "2");
        List<NoticeDto> recycleList = noticeCustomerMapper.getNoticeByStatus(map);
        ntd.setUnreadList(unreadList);
        ntd.setReadList(readList);
        ntd.setRecycleList(recycleList);
        return ntd;

    }

    public void markAsReadById(Integer id) {
        Notice notice = noticeMapper.selectByPrimaryKey(id);
        Integer companyId = SecurityUtils.getCurrentCompanyId();
        Integer user_id = SecurityUtils.getCurrentUserId();
        if (notice != null) {
            //查询用户通知表中是否有记录 有记录则标记为已读，无则为系统通知 增加一条记录
            Map map = new HashMap();
            map.put("id", id);
            map.put("userId", user_id);
            map.put("companyId", companyId);
            NoticeUser nc = noticeCustomerMapper.selectNoticeCustomerByNoticeId(map);
            if (nc != null) {
                //更改状态 status=1 记录阅读时间
                nc.setStatus(1);
                nc.setReadTime(new Date());
                nc.setReceiveCompanyId(companyId);
                int num = noticeCustomerMapper.updateByPrimaryKey(nc);
                Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);
            } else {
                //新增一条用户通知记录 并标记为已读
                NoticeUser newNc = new NoticeUser();
                newNc.setNoticeId(notice.getId());
                newNc.setReceiveId(user_id);
                newNc.setCreatedTime(new Date());
                newNc.setReadTime(new Date());
                newNc.setStatus(1);
                newNc.setSendId(notice.getCreatedBy());
                newNc.setDeleteFlag(0);
                newNc.setReceiveCompanyId(companyId);
                int num = noticeCustomerMapper.insert(newNc);
                Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
            }
        }
    }

    public void deleteNoticeById(Integer id) {
        Notice notice = noticeMapper.selectByPrimaryKey(id);
        Integer companyId = SecurityUtils.getCurrentCompanyId();
        Integer user_id = SecurityUtils.getCurrentUserId();
        if (notice != null) {
            Map map = new HashMap();
            map.put("id", id);
            NoticeUser nc = noticeCustomerMapper.selectNoticeCustomerByNoticeId(map);
            if (nc != null) {
                nc.setDeleteFlag(1);
                nc.setReceiveCompanyId(companyId);
                int num = noticeCustomerMapper.updateByPrimaryKey(nc);
                Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);
            }
        }

    }


}
