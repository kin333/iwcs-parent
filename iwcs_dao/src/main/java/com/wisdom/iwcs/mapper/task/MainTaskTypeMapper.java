package com.wisdom.iwcs.mapper.task;


import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.dto.MainTaskTypeAndAreaCode;
import com.wisdom.iwcs.domain.task.MainTaskType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-06-25 14:20:51.
 */
@Repository
public interface MainTaskTypeMapper extends MyMapperAndIds<MainTaskType> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<MainTaskType> selectPage(Map map);

    MainTaskType selectByMainTaskTypeCode(String mainTaskTypeCode);

    List<MainTaskType> selectAllTaskType();

    List<MainTaskTypeAndAreaCode> selectTaskTypeWithAreaCode(String areaCode);

}