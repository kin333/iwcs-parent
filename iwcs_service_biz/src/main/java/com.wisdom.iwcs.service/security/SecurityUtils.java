package com.wisdom.iwcs.service.security;

import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.system.SUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {

    private SecurityUtils() {
    }

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user
     */
    public static String getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                //instanceof 用来判断对象是否是特定类的实例。
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                userName = springSecurityUser.getUsername().split(",")[0];
            } else if (authentication.getPrincipal() instanceof String) {
                userName = (String) authentication.getPrincipal();
            } else if (authentication.getPrincipal() instanceof TokenUser) {
                TokenUser tokenUser = (TokenUser) authentication.getPrincipal();
                userName = tokenUser.getUsername();
            }
        }
        return userName;
    }

    public static SUser getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        SUser tokenUser = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof TokenUser) {
                tokenUser = ((TokenUser) authentication.getPrincipal()).getUser();
            }
        }
        return tokenUser;
    }

    /***
     * 通过token获得当前的userId
     * @return
     */
    public static Integer getCurrentUserId() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Integer userId = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof TokenUser) {
                userId = ((TokenUser) authentication.getPrincipal()).getUserId();
            }
        }
        return userId;
    }

    /***
     * 通过token获得当前的员工id
     * @return
     */
    public static Integer getCurrentEmployeeId() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Integer employeeId = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof TokenUser) {
                employeeId = ((TokenUser) authentication.getPrincipal()).getEmployeeId();
                if (employeeId == null) {
                    throw new BusinessException("该用户未在oa系统中注册");
                }
            }
        }
        return employeeId;
    }

    /**
     * 获取当前登录公司companyId
     */
    public static Integer getCurrentCompanyId() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Integer companyId = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof TokenUser) {
                companyId = ((TokenUser) authentication.getPrincipal()).getCurrentCompanyId();
            }
        }
        return companyId;
    }

    /**
     * 获取当前登录的库区
     *
     * @return
     */
    public static String getCurrentAreaCode() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String areaCode = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof TokenUser) {
                areaCode = ((TokenUser) authentication.getPrincipal()).getAreaCode();
            }
        }
        return areaCode;
    }

    /***
     * 当前登录用户是否是超级管理员
     */
    public static Boolean isSuperAdmin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Boolean isSuperAdmin = false;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof TokenUser) {
                isSuperAdmin = ((TokenUser) authentication.getPrincipal()).getSuperAdmin();
            }
        }
        return isSuperAdmin;
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise
     */
    public static boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Collection<? extends GrantedAuthority> authorities = securityContext.getAuthentication().getAuthorities();
        if (authorities != null) {
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(AuthoritiesConstants.ANONYMOUS)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * If the current user has a specific authority (security role).
     *
     * <p>The name of this method comes from the isUserInRole() method in the Servlet API</p>
     *
     * @param authority the authorithy to check
     * @return true if the current user has the authority, false otherwise
     */
    public static boolean isCurrentUserInRole(String authority) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                return springSecurityUser.getAuthorities().contains(new SimpleGrantedAuthority(authority));
            }
        }
        return false;
    }
}
