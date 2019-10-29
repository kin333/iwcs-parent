package com.wisdom.iwcs.mapper.log;

import com.wisdom.iwcs.domain.log.dto.HisDataMigrationDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface HisDataMigrationMapper {

   int taskOperationLogHis(HisDataMigrationDTO hisDataMigrationDTO);

   int taskOperationLog(HisDataMigrationDTO hisDataMigrationDTO);
}
