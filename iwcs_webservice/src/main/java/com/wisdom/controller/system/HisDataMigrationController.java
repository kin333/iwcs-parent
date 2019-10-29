package com.wisdom.controller.system;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.service.sysbase.HisDataMigrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/api/wisdom/hisDataMigration")
public class HisDataMigrationController {
    @Autowired
    HisDataMigrationService hisDataMigrationService;

    @PostMapping
    public Result hisDataMigration(){
        hisDataMigrationService.dataMigration();
        return new Result();
    }
}
