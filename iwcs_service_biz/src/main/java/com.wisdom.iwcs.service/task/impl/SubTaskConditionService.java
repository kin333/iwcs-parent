package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbitmq.client.*;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.RabbitMQUtil;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.common.utils.taskUtils.ConsumerThread;
import com.wisdom.iwcs.domain.task.SubConditionRouteKey;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.domain.task.dto.SubTaskConditionDTO;
import com.wisdom.iwcs.mapper.task.SubConditionRouteKeyMapper;
import com.wisdom.iwcs.mapper.task.SubTaskConditionMapper;
import com.wisdom.iwcs.mapstruct.task.SubTaskConditionMapStruct;
import com.wisdom.iwcs.service.log.logImpl.RabbitMQPublicService;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.task.action.RouseMainTaskAction;
import com.wisdom.iwcs.service.task.intf.ISubTaskConditionsService;
import liquibase.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeoutException;

@Service
@Transactional(rollbackFor = Exception.class)
public class SubTaskConditionService implements ISubTaskConditionsService {

    private final Logger logger = LoggerFactory.getLogger(SubTaskConditionService.class);

    private final SubTaskConditionMapper subTaskConditionMapper;

    private final SubTaskConditionMapStruct subTaskConditionMapStruct;
    @Autowired
    SubConditionRouteKeyMapper subConditionRouteKeyMapper;

    @Autowired
    public SubTaskConditionService(SubTaskConditionMapStruct subTaskConditionMapStruct, SubTaskConditionMapper subTaskConditionMapper) {
        this.subTaskConditionMapper = subTaskConditionMapper;
        this.subTaskConditionMapStruct = subTaskConditionMapStruct;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link SubTaskConditionDTO }
     *
     * @return int
     */
    @Override
    public int insert(SubTaskConditionDTO record) {
        SubTaskCondition tsSubTaskCondition = subTaskConditionMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        tsSubTaskCondition.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
//        tsSubTaskCondition.setCreatedTime(new Date());
//        tsSubTaskCondition.setCreatedBy(userId);
//        tsSubTaskCondition.setLastModifiedBy(userId);
//        tsSubTaskCondition.setLastModifiedTime(new Date());

        int num = subTaskConditionMapper.insert(tsSubTaskCondition);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List< SubTaskConditionDTO > }
     *
     * @return int
     */
    @Override
    public int insertBatch(List<SubTaskConditionDTO> records) {
        List<SubTaskCondition> recordList = subTaskConditionMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
//        recordList.forEach(record -> {
//            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
//            record.setCreatedTime(new Date());
//            record.setCreatedBy(userId);
//            record.setLastModifiedBy(userId);
//            record.setLastModifiedTime(new Date());
//        });

        int num = subTaskConditionMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link SubTaskConditionDTO }
     */
    @Override
    public SubTaskConditionDTO selectByPrimaryKey(Integer id) {

        SubTaskCondition tsSubTaskCondition = subTaskConditionMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(tsSubTaskCondition, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return subTaskConditionMapStruct.toDto(tsSubTaskCondition);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link SubTaskConditionDTO }
     *
     * @return {@link List< SubTaskConditionDTO > }
     */
    @Override
    public List<SubTaskConditionDTO> selectSelective(SubTaskConditionDTO record) {
        SubTaskCondition tsSubTaskCondition = subTaskConditionMapStruct.toEntity(record);

        List<SubTaskCondition> tsSubTaskConditionList = subTaskConditionMapper.select(tsSubTaskCondition);
        return subTaskConditionMapStruct.toDto(tsSubTaskConditionList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link SubTaskConditionDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKey(SubTaskConditionDTO record) {
        SubTaskCondition tsSubTaskCondition = subTaskConditionMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        tsSubTaskCondition.setLastModifiedBy(userId);
//        tsSubTaskCondition.setLastModifiedTime(new Date());

        int num = subTaskConditionMapper.updateByPrimaryKey(tsSubTaskCondition);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link SubTaskConditionDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(SubTaskConditionDTO record) {
        SubTaskCondition tsSubTaskCondition = subTaskConditionMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        tsSubTaskCondition.setLastModifiedBy(userId);
//        tsSubTaskCondition.setLastModifiedTime(new Date());

        int num = subTaskConditionMapper.updateByPrimaryKeySelective(tsSubTaskCondition);
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
    @Override
    public int deleteByPrimaryKey(Integer id) {
        int num = subTaskConditionMapper.deleteByPrimaryKey(id);
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
//        return subTaskConditionMapper.deleteLogicByPrimaryKey(id);
//    }

    /**
     * 根据主键删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids){
        return subTaskConditionMapper.deleteByIds(String.join(",", ids));
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
//        return subTaskConditionMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData< SubTaskConditionDTO > }
     */
    @Override
    public GridReturnData<SubTaskConditionDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<SubTaskConditionDTO> mGridReturnData = new GridReturnData<>();
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

        List<SubTaskCondition> list = subTaskConditionMapper.selectPage(map);

        PageInfo<SubTaskCondition> pageInfo = new PageInfo<>(list);
        PageInfo<SubTaskConditionDTO> pageInfoFinal = new PageInfo<>(subTaskConditionMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 消息队列订阅事件
     * @return
     */
    public boolean loginListenner(String subTaskNum) {
        //查询子任务单对应的事件
        List<String> eventList = getEventList(subTaskNum);
        logger.info("子任务单{}开始订阅事件", subTaskNum);
        if (eventList == null || eventList.size() <= 0) {
            return false;
        }

        int i = 0;
        for (String event : eventList) {
            SubConditionRouteKey subConditionRouteKey = subConditionRouteKeyMapper.selectByCode(event);
            if (subConditionRouteKey == null) {
                continue;
            }
            String queueName = "Event" + i++ + "_" + subTaskNum;
            Thread thread = new Thread(new ConsumerThread(queueName, subConditionRouteKey.getRouteKey(), new RouseMainTaskAction()));
            thread.start();
            logger.info("子任务{}的订阅事件{}已开始,中文名:{},routeKey:{}", subTaskNum,subConditionRouteKey.getRouteKeyCode(),
                    subConditionRouteKey.getRouteKeyName(), subConditionRouteKey.getRouteKey());
        }

        logger.info("子任务单{}订阅事件完成", subTaskNum);
        return true;
    }

    public boolean deleteListenner(String subTaskNum) {
        //查询子任务单对应的事件
        List<String> eventList = getEventList(subTaskNum);
//        logger.info("子任务单{}开始取消订阅事件", subTaskNum);
        if (eventList == null || eventList.size() <= 0) {
            return false;
        }
        for (String event : eventList) {
            SubConditionRouteKey subConditionRouteKey = subConditionRouteKeyMapper.selectByCode(event);
            if (subConditionRouteKey == null) {
                continue;
            }
            //发送结束标识
            RabbitMQPublicService.sendEndLogo(subConditionRouteKey.getRouteKey(), subTaskNum);
//            logger.info("子任务{}的订阅事件{}已发送结束标识,中文名:{},routeKey:{}", subTaskNum,subConditionRouteKey.getRouteKeyCode(),
//                    subConditionRouteKey.getRouteKeyName(), subConditionRouteKey.getRouteKey());
        }
//        logger.info("子任务单{}取消订阅事件完成", subTaskNum);
        return true;
    }

    /**
     * 查询子任务单对应的事件
     * @param subTaskNum
     * @return
     */
    private List<String> getEventList(String subTaskNum) {
        if (StringUtils.isEmpty(subTaskNum)) {
            throw new BusinessException("消息队列订阅或取消事件时,子单号不能为空");
        }
        //查询子任务单对应的事件
        List<SubTaskCondition> subTaskConditionList = subTaskConditionMapper.selectByTaskNum(subTaskNum);
        List<String> eventList = new ArrayList<>();
        for (SubTaskCondition subTaskCondition : subTaskConditionList) {
            String subscribeEvent = subTaskCondition.getSubscribeEvent();
            if (StringUtils.isNotEmpty(subscribeEvent)) {
                //将事件名称拆分出来
                String[] split = subscribeEvent.split(",");
                eventList.addAll(Arrays.asList(split));
            }
        }
        return eventList;
    }
}
