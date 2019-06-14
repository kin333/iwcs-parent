package com.wisdom.iwcs.mapper.inv;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.inv.InvTask;
import com.wisdom.iwcs.domain.inv.dto.InvTaskSearchDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-03-01 09:45:39.
 */
@Repository
public interface InvTaskMapper extends MyMapperAndIds<InvTask> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<InvTask> selectPage(Map map);

    int saveInvTaskInfo(InvTask invTask);

    int updateTheFinalStatusOfInvTask(InvTask invTask);

    List<InvTask> selectInvTask(InvTaskSearchDto invTaskSearchDto);

}
