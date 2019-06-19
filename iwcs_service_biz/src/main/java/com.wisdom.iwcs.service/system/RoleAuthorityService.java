package com.wisdom.iwcs.service.system;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.system.Authority;
import com.wisdom.iwcs.domain.system.DataFilterRule;
import com.wisdom.iwcs.domain.system.RoleAuthority;
import com.wisdom.iwcs.domain.system.SOperation;
import com.wisdom.iwcs.domain.system.dto.DataFilterRuleDto;
import com.wisdom.iwcs.domain.system.dto.RoleAuthDTO;
import com.wisdom.iwcs.domain.system.dto.SOperationDto;
import com.wisdom.iwcs.mapper.system.AuthorityMapper;
import com.wisdom.iwcs.mapper.system.DataFilterRuleMapper;
import com.wisdom.iwcs.mapper.system.RoleAuthorityMapper;
import com.wisdom.iwcs.mapper.system.SOperationMapper;
import com.wisdom.iwcs.mapstruct.system.DataRuleMapStruct;
import com.wisdom.iwcs.mapstruct.system.SOperationMapStruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleAuthorityService {
    private final Logger logger = LoggerFactory.getLogger(RoleAuthorityService.class);
    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Autowired
    SOperationMapper operationMapper;

    @Autowired
    SOperationMapStruct sOperationMapStruct;

    @Autowired
    DataFilterRuleMapper dataFilterRuleMapper;

    @Autowired
    DataRuleMapStruct dataRuleMapStruct;

    @Autowired
    private AuthorityMapper authorityMapper;

    public int add(RoleAuthority roleAuthority) {
        if (logger.isInfoEnabled()) {
            logger.info("添加角色权限关系:[信息:{}]", roleAuthority);
        }
        int num = roleAuthorityMapper.insertSelective(roleAuthority);
        return num;
    }


    public int addBatch(List<RoleAuthority> roleAuthorities) {
        if (logger.isInfoEnabled()) {
            logger.info("添加角色权限关系(批量):[信息:{}]", roleAuthorities);
        }
        int num = roleAuthorityMapper.insertInBatch(roleAuthorities);
        return num;
    }


    public int update(RoleAuthority roleAuthority) {
        if (logger.isInfoEnabled()) {
            logger.info("更新角色权限关系:[信息:{}]", roleAuthority);
        }
        int num = roleAuthorityMapper.updateByIdSelective(roleAuthority);
        return num;
    }


    public int removePhysicalById(int id) {
        if (logger.isInfoEnabled()) {
            logger.info("删除角色权限关系:[条件:{}]", id);
        }
        int num = roleAuthorityMapper.deleteById(id);
        return num;
    }


    public List<RoleAuthority> listByRoleId(List<Integer> ids) {
        if (logger.isInfoEnabled()) {
            logger.info("查询角色权限关系列表(按角色id):[条件:{}]", ids);
        }
        List<RoleAuthority> roleAuthorities = roleAuthorityMapper.selectByRoleIds(ids);
        return roleAuthorities;
    }


    public List<RoleAuthority> listByRoleId(Integer id) {
        if (logger.isInfoEnabled()) {
            logger.info("查询角色权限关系列表(按角色id):[条件:{}]", id);
        }
        List<RoleAuthority> roleAuthorities = roleAuthorityMapper.selectByRoleId(id);
        return roleAuthorities;
    }


    public int removePhysicalByObject(RoleAuthority roleAuthority) {
        if (logger.isInfoEnabled()) {
            logger.info("删除角色权限关系:[条件:{}]", roleAuthority);
        }
        int num = roleAuthorityMapper.deleteByObject(roleAuthority);
        return num;
    }


    public int updateByObject(RoleAuthority roleAuthority) {
        if (logger.isInfoEnabled()) {
            logger.info("更新角色权限关系:[信息:{}]", roleAuthority);
        }
        int num = roleAuthorityMapper.updateByObject(roleAuthority);
        return num;
    }


    public RoleAuthority getOne(RoleAuthority roleMenuCondition) {
        if (logger.isInfoEnabled()) {
            logger.info("查询角色权限关系:[信息:{}]", roleMenuCondition);
        }
        RoleAuthority roleAuthority = roleAuthorityMapper.selectOneByObject(roleMenuCondition);
        return roleAuthority;
    }

    public List<SOperationDto> getValidOperation(Integer roleId, Integer menuId) {
        List<SOperation> operationList = operationMapper.getByMenuId(menuId);
        List<RoleAuthority> roleAuthorityList = this.listByRoleId(roleId);

        List<SOperationDto> operationDtoList = sOperationMapStruct.toDto(operationList);
        for (RoleAuthority roleAuthority : roleAuthorityList) {
            for (SOperationDto operationDto : operationDtoList) {
                if (operationDto.getAuthorityId().equals(roleAuthority.getAuthorityId())) {
                    operationDto.setValid(true);
                    break;
                }
            }
        }

        return operationDtoList;
    }

    /**
     * 获取按钮权限
     */
    public Result getMenuButtons(Integer roleId, Integer menuId) {
        List<RoleAuthority> roleAuthorityList = this.listByRoleId(roleId);
        List<Authority> authorities = authorityMapper.getButtonAuthByParentId(menuId);
        List<Integer> checkedIds = roleAuthorityList.stream().map(RoleAuthority::getAuthorityId).collect(Collectors.toList());
        //todo
        authorities.forEach(auth -> {
            if (checkedIds.contains(auth.getId())) {
                auth.setChecked(true);
            }
        });
        return new Result(authorities);
    }

    public List<DataFilterRuleDto> getValidDataRule(Integer roleId, Integer menuId) {
        DataFilterRule dataFilterRule = new DataFilterRule();
        dataFilterRule.setMenuId(menuId);
        List<DataFilterRule> dataFilterRuleList = dataFilterRuleMapper.selectByObject(dataFilterRule);

        List<RoleAuthority> roleAuthorityList = this.listByRoleId(roleId);

        List<DataFilterRuleDto> dataFilterRuleDtoList = dataRuleMapStruct.toDto(dataFilterRuleList);

        for (RoleAuthority roleAuthority : roleAuthorityList) {
            for (DataFilterRuleDto dto : dataFilterRuleDtoList) {
                if (dto.getAuthorityId().equals(roleAuthority.getAuthorityId())) {
                    dto.setValid(true);
                    break;
                }
            }
        }

        return dataFilterRuleDtoList;
    }

    public Result updateRoleAuth(RoleAuthDTO roleAuthDTO) {
        if (roleAuthDTO.getAuthType() == 2) {
            roleAuthorityMapper.deleteByRoleIdAndAuthTypeAndAuthParentId(roleAuthDTO.getRoleId(), roleAuthDTO.getAuthType(), roleAuthDTO.getAuthParentId());
        } else {
            roleAuthorityMapper.deleteByRoleIdAndAuthType(roleAuthDTO.getRoleId(), roleAuthDTO.getAuthType());
        }
        List<RoleAuthority> roleAuthorityList = roleAuthDTO.getAuthIdList().stream().map(roleAuth -> {
            RoleAuthority roleAuthority = new RoleAuthority();
            roleAuthority.setRoleid(roleAuthDTO.getRoleId());
            roleAuthority.setAuthorityId(roleAuth);
            return roleAuthority;
        }).collect(Collectors.toList());
        if (roleAuthorityList != null && roleAuthorityList.size() > 0) {
            roleAuthorityMapper.insertInBatch(roleAuthorityList);
        }
        return new Result();
    }
}
