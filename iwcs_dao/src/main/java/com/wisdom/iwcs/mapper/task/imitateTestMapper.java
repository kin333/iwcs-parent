package com.wisdom.iwcs.mapper.task;

import com.wisdom.iwcs.domain.task.imitateTest;
import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-10-11 15:47:08.
 */
@Repository
public interface imitateTestMapper extends  MyMapperAndIds<imitateTest> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<imitateTest> selectPage(Map map);
}