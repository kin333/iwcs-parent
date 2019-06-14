package com.wisdom.security;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class CusAuthenticationDetails extends WebAuthenticationDetails {
    /**
     * 公司id
     */
    private String companyId;

    public CusAuthenticationDetails(HttpServletRequest request) {
        super(request);
    }
}
