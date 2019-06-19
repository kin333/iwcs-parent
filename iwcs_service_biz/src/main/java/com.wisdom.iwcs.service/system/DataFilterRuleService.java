package com.wisdom.iwcs.service.system;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.system.Authority;
import com.wisdom.iwcs.domain.system.DataFilterRule;
import com.wisdom.iwcs.domain.system.RoleAuthority;
import com.wisdom.iwcs.domain.system.SUser;
import com.wisdom.iwcs.mapper.system.*;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class DataFilterRuleService {
    private static final Logger logger = LoggerFactory.getLogger(DataFilterRuleService.class);
    @Autowired
    private DataFilterRuleMapper dataFilterRuleMapper;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private SUserMapper sUserMapper;
    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;
    @Autowired
    private SUserService sUserService;

    public int add(DataFilterRule dataFilterRule) {
        if (logger.isDebugEnabled()) {
            logger.debug("过滤规则(添加)[主业务对象:{}]", dataFilterRule);
        }

        SUser user = SecurityUtils.getCurrentUser();
        Date currentDate = new Date();
        Integer createUserId = user.getId();
        String createUserName = user.getUserName();
        dataFilterRule.setCreateBy(String.valueOf(createUserId));
        dataFilterRule.setCreateName(createUserName);
        dataFilterRule.setCreateDate(currentDate);
        dataFilterRule.setUpdateBy(String.valueOf(createUserId));
        dataFilterRule.setUpdateName(createUserName);
        dataFilterRule.setUpdateDate(currentDate);

        int num = dataFilterRuleMapper.insert(dataFilterRule);
        return num;
    }

    public int update(DataFilterRule dataFilterRule) {
        if (logger.isDebugEnabled()) {
            logger.debug("过滤规则(更新)[主业务对象:{}]", dataFilterRule);
        }

        SUser user = SecurityUtils.getCurrentUser();
        Date currentDate = new Date();
        Integer createUserId = user.getId();
        String createUserName = user.getUserName();
        dataFilterRule.setUpdateBy(String.valueOf(createUserId));
        dataFilterRule.setUpdateName(createUserName);
        dataFilterRule.setUpdateDate(currentDate);

        int num = dataFilterRuleMapper.updateByIdSelective(dataFilterRule);
        return num;
    }

    public List<DataFilterRule> list(DataFilterRule rule, Integer page, Integer rows) {
        //TODO：
        return null;
    }

    public List<DataFilterRule> list(DataFilterRule rule) {
        if (logger.isDebugEnabled()) {
            logger.debug("过滤规则(查询)[查询条件:{}]", rule);
        }

        List<DataFilterRule> rules = dataFilterRuleMapper.selectByObject(rule);
        return rules;
    }

    public DataFilterRule getOne(DataFilterRule dataFilterRule) {
        if (logger.isDebugEnabled()) {
            logger.debug("过滤规则(查询)[查询条件:{}]", dataFilterRule);
        }

        DataFilterRule result = dataFilterRuleMapper.selectOneByObject(dataFilterRule);
        return result;
    }

    public DataFilterRule getOne(Integer id) {
        if (logger.isDebugEnabled()) {
            logger.debug("过滤规则(查询)[查询条件:{}]", id);
        }

        DataFilterRule result = dataFilterRuleMapper.selectOneById(id);
        return result;
    }

    public int removePhysicalById(Integer id) {
        if (logger.isDebugEnabled()) {
            logger.debug("过滤规则(删除)[删除条件:{}]", id);
        }

        int num = dataFilterRuleMapper.deleteById(id);
        return num;
    }


    public int removePhysicalById(List<Integer> ids) {
        if (logger.isDebugEnabled()) {
            logger.debug("过滤规则(批量删除)[删除条件:{}]", ids);
        }

        int num = dataFilterRuleMapper.deleteByIds(ids);
        //TODO：删除对应权限关系表中绑定的ruleid，或者放弃不管
        return num;
    }

    public List<DataFilterRule> listByMenuName(String name) {
        //获取菜单对应id
        //获取用户对应角色
        //获取用户对应角色-菜单
        //获取数据过滤规则id,去重
        //获取数据过滤规则
        Authority authority = new Authority();
        authority.setName(name);
        Authority authorityResult = authorityMapper.selectOneByObject(authority);
        if (authorityResult == null) {
            //查询不到权限，返回空
            return new ArrayList<>();
        }
        SUser currentSUser = this.getCurrentUserByUserId(SecurityUtils.getCurrentUserId());
        List<Integer> roleIds = sUserService.getUserAllRoleIdsByUserId(currentSUser.getId());
        if (roleIds == null || roleIds.size() < 1) {
            //当前用户未设置角色，返回空
            return new ArrayList<>();
        }

        List<Integer> validRoleIds = sUserService.getValidRoleIds(roleIds);
        if (validRoleIds.size() <= 0) {
            ////当前用户未有可用角色，返回空
            return new ArrayList<>();
        }
        System.out.println("--------------->" + authorityResult.getId());
        List<RoleAuthority> roleAuthorities = roleAuthorityMapper.selectByRoleIdsAndMenuId(validRoleIds, authorityResult.getId());
        if (roleAuthorities.size() <= 0) {
            //查询不到角色-权限关系表记录,返回空
            return new ArrayList<>();
        }

        Set dataRuleIds = new HashSet();

        roleAuthorities.stream().forEach(
                roleAuthority -> {
                    if (!Strings.isNullOrEmpty(roleAuthority.getDataRule())) {
                        List<Integer> temp = Splitter.on(",")
                                .trimResults()
                                .omitEmptyStrings()
                                .splitToList(roleAuthority.getDataRule())
                                .stream()
                                .map(s -> Integer.valueOf(s))
                                .collect(toList());
                        dataRuleIds.addAll(temp);
                    }
                }
        );
        if (dataRuleIds.size() <= 0) {
            ////当前用户未有过滤，返回空
            return new ArrayList<>();
        }

        List<DataFilterRule> rules = dataFilterRuleMapper.selectByIds(Lists.newArrayList(dataRuleIds));
        return rules;
    }


    private SUser getCurrentUserByUserId(Integer userId) {
        SUser sUser = sUserMapper.selectByPrimaryKey(userId);
        Preconditions.checkNotNull(sUser, ApplicationErrorEnum.USER_NOT_FOUND);
        return sUser;
    }
}
