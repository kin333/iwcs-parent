package com.wisdom.service.system;

import com.wisdom.license.DecryptMessage;
import com.wisdom.license.LicenseCheckService;
import com.wisdom.license.SecretMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Devin
 * @date 2018-06-19.
 */
@Service
public class LicenseService {
    private final Logger logger = LoggerFactory.getLogger(LicenseService.class);

    private String licenseFilePath = "/license.bat";
    private String secringFilePath = "/secring.gpg";
    private String password = "wisdom56";

    public SecretMessage getMessageFromLicense() {
        String licensePath = this.getClass().getResource(licenseFilePath).getPath();
        String secringPath = this.getClass().getResource(secringFilePath).getPath();
        try {
            SecretMessage messageFromLicense = DecryptMessage.getMessageFromLicense(licensePath, secringPath, password);
            return messageFromLicense;
        } catch (Exception e) {
            logger.error("license解析出错");
            e.printStackTrace();
        }
        return null;
    }

    public Boolean licenseCheck() {
        String licensePath = this.getClass().getResource(licenseFilePath).getPath();
        String secringPath = this.getClass().getResource(secringFilePath).getPath();
        if (licensePath == null || secringPath == null) {
            System.out.println("路径出错");
            return false;
        }
        return LicenseCheckService.check(licensePath, secringPath, password);
    }
}
