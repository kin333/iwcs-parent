package com.wisdom.iwcs.mapper.task;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.BaseMsgSend;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-09-02 14:24:24.
 */
@Repository
public interface BaseMsgSendMapper extends MyMapperAndIds<BaseMsgSend> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<BaseMsgSend> selectPage(Map map);

    /**
     * 查询所有未发送的消息
     * @return
     */
    List<BaseMsgSend> selectAllNoSend();
}