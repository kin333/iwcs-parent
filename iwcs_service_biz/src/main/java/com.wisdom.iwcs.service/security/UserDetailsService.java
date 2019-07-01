package com.wisdom.iwcs.service.security;

import com.google.common.base.Splitter;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.system.Role;
import com.wisdom.iwcs.domain.system.SUser;
import com.wisdom.iwcs.mapper.system.*;
import com.wisdom.iwcs.service.system.SUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private SUserMapper sUserMapper;


    @Autowired
    UserInfoExtMapper userInfoExtMapper;
    @Autowired
    private SUserService sUserService;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String name) {
        String userName = "";
        String areaCodeTmp = "";
        if (name != null) {
            String[] nameAndCompanyId = name.split(",");
            userName = nameAndCompanyId[0];
//            companyIdTemp = nameAndCompanyId[1];
            areaCodeTmp = nameAndCompanyId[1];
        }
        final String areaCode = areaCodeTmp;
        if (log.isDebugEnabled()) {
            log.debug("Authenticating:[用户名:{}]", name);
        }
        String lowercaseName = userName;//.toLowerCase();
        //XXX:状态码 0,1 放到静态类中去
        Optional<SUser> userFromDatabase = Optional.ofNullable(sUserMapper.findOneByUserNameAndStatus(lowercaseName, "1"));
        return userFromDatabase.map(user -> {
            //XXX:状态码 0,1 放到静态类中去
            if ("0".equals(user.getStatus())) {
                throw new UserNotActivatedException("User " + lowercaseName + " was not activated");
            }
            //TODO: 特殊角色(不需要授权即可拥有全部权限的角色,会造成硬编码，需要注意)

            Set<String> userAuthorities = sUserService.getAuthoritiesByUserIdAndCompanyId(user.getId(),"1");

            List<GrantedAuthority> grantedAuthorities  = userAuthorities.stream()
                    .map(authorityName -> new SimpleGrantedAuthority(authorityName))
                    .collect(toList());
//            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            //XXX:添加默认角色，防止出现未设置任何角色情况下,出现A granted authority textual representation is required错误
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_DEFAULT"));
//            user.setAuthorities(null);
            if (log.isDebugEnabled()) {
                log.debug("用户授权信息加载...:[用户名:{},用户密码:{},用户角色:{}]", lowercaseName, "*****", grantedAuthorities);
            }

//            UserInfoExt userInfoExt = new UserInfoExt();
//            userInfoExt.setUserId(user.getId());
//            List<UserInfoExt> userInfoExtList = userInfoExtMapper.selectSelective(userInfoExt);
//            Preconditions.checkArgument(userInfoExtList!=null && userInfoExtList.size()==1, ApplicationErrorEnum.USER_NOT_FOUND);
//            user.setUserInfo(userInfoExtList.get(0));

            TokenUser tokenUser = new TokenUser(lowercaseName, user.getPassword(), grantedAuthorities);
            tokenUser.setUserId(user.getId());
            tokenUser.setUser(user);
            tokenUser.setSuperAdmin(1 == user.getSuperAdminFlag());


            //设置当前登录公司信息
//            tokenUser.setCurrentCompanyId(Integer.parseInt(companyId));
            tokenUser.setAreaCode(areaCode);
            return tokenUser;
        }).orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseName
                + " was not found in the database"));
    }


    private List<Integer> getValidRoleIds(String roleIdStr) {
        List<Integer> roleIds = Splitter
                .on(",")
                .trimResults()
                .omitEmptyStrings()
                .splitToList(roleIdStr)
                .stream().map(s -> Integer.valueOf(s))
                .collect(toList());
        Preconditions.checkArgument(roleIds.size() > 0, ApplicationErrorEnum.USER_NOT_HAVE_ANY_ROLES);
        //查询所有有效的角色
        Role roleCondition = new Role();
        roleCondition.setDeleteFlag(false);
        List<Role> roles = roleMapper.selectByObject(roleCondition);
        Preconditions.checkArgument(roles.size() > 0, ApplicationErrorEnum.USER_NOT_HAVE_ANY_ROLES);

        List<Integer> allVialdRoleIds = roles
                .stream()
                .map(Role::getId)
                .collect(toList());

        return roleIds
                .stream()
                .filter(value -> allVialdRoleIds.contains(value))
                .collect(toList());
    }

    private SUser getCurrentUserByUserId(Integer userId) {
        SUser sUser = sUserMapper.selectByPrimaryKey(userId);
        Preconditions.checkNotNull(sUser, ApplicationErrorEnum.USER_NOT_FOUND);
        return sUser;
    }

}
