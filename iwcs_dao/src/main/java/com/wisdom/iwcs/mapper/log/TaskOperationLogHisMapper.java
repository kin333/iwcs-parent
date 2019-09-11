package com.wisdom.iwcs.mapper.log;

import org.springframework.stereotype.Repository;

@Repository
public interface TaskOperationLogHisMapper {
   //把taskOperationLog表中7天前的数据刷到taskOperationLogHis表
   int taskOperationLogHis();
   //在taskOperationLog表中删除已经迁移完成的数据
   int taskOperationLog();
}
