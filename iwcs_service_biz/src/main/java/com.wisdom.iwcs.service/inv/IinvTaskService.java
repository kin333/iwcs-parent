package com.wisdom.iwcs.service.inv;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.inv.InvTask;
import com.wisdom.iwcs.domain.inv.dto.InvTaskDTO;
import com.wisdom.iwcs.domain.inv.dto.InvTaskSearchDto;

import java.util.List;

/**
 * @Auther: panzun
 * @Date: 2019-3-15 15:10
 * @Description:
 */
public interface IinvTaskService {
    /**
     * 生成盘点任务
     *
     * @param record
     * @return
     */
    List<String> createInvTask(InvTaskDTO record);

    /**
     * 条件查询inv任务
     *
     * @param record
     * @return
     */
    List<InvTaskDTO> criteriaQueryInvTask(InvTaskDTO record);

    /**
     * 分页查询
     *
     * @param gridPageRequest
     * @return
     */
    GridReturnData<InvTaskDTO> selectPage(GridPageRequest gridPageRequest);

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    List<InvTask> selectInvTask(InvTaskSearchDto invTaskSearchDto);

}
