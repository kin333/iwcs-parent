package com.wisdom.config;

import com.wisdom.iwcs.service.system.LicenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Devin
 * @date 2018-06-19.
 */
@Component
public class LicenseInitialCheck implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(LicenseInitialCheck.class);
    @Autowired
    private LicenseService licenseService;


    @Override
    public void afterPropertiesSet() {

//        Boolean licenseCheck = licenseService.licenseCheck();
//        if(!licenseCheck){
//            logger.error("license检测出错");
//            System.exit(1);
//        }
    }
}
