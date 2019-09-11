package com.wisdom.iwcs.service.sysbase;

import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.mapper.log.TaskOperationLogHisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskOperationLogHisService {
    private final Logger logger = LoggerFactory.getLogger(TaskOperationLogHisService.class);

    @Autowired
    TaskOperationLogHisMapper taskOperationLogHisMapper;

    /**
     * 迁移taskOperationLog表中7天前的数据到taskOperationLogHis表
     * @return
     */
    public int insert(){
        int ret=0;
        //把taskOperationLog表中7天前的数据刷到taskOperationLogHis表
        int num = taskOperationLogHisMapper.taskOperationLogHis();
        if (num>0){
            //在taskOperationLog表中删除已经迁移完成的数据
            ret=taskOperationLogHisMapper.taskOperationLog();
            Preconditions.checkArgument(ret > 0, ApplicationErrorEnum.COMMON_FAIL);
        }
        return ret;
    }

}
