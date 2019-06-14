package com.wisdom.service.system;

import com.wisdom.controller.mapstruct.system.*;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.system.Authority;
import com.wisdom.iwcs.domain.system.DataFilterRule;
import com.wisdom.iwcs.domain.system.RoleAuthority;
import com.wisdom.iwcs.domain.system.SOperation;
import com.wisdom.iwcs.domain.system.dto.AuthorityDto;
import com.wisdom.iwcs.domain.system.dto.DataFilterRuleDto;
import com.wisdom.iwcs.domain.system.dto.MenuTreeDto;
import com.wisdom.iwcs.domain.system.dto.SOperationDto;
import com.wisdom.iwcs.mapper.system.AuthorityMapper;
import com.wisdom.iwcs.mapper.system.DataFilterRuleMapper;
import com.wisdom.iwcs.mapper.system.RoleAuthorityMapper;
import com.wisdom.iwcs.mapper.system.SOperationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AuthorityService {
    private Logger logger = LoggerFactory.getLogger(AuthorityService.class);
    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    SOperationMapper operationMapper;

    @Autowired
    DataFilterRuleMapper dataFilterRuleMapper;


    @Autowired
    RoleAuthorityMapper roleAuthorityMapper;

    @Autowired
    AuthorityMapStruct authorityMapStruct;

    @Autowired
    AuthorityOperationMapStruct authorityOperationMapStruct;

    @Autowired
    AuthorityDataRuleMapStruct authorityDataRuleMapStruct;

    @Autowired
    SOperationMapStruct operationMapStruct;

    @Autowired
    DataRuleMapStruct dataRuleMapStruct;
    @Autowired
    private RoleAuthorityService roleAuthorityService;


    public int add(AuthorityDto authorityDto) {
        if (logger.isInfoEnabled()) {
            logger.info("添加权限:[权限信息:{}]", authorityDto);
        }

        Authority authority = authorityMapStruct.toEntity(authorityDto);

        int count = authorityMapper.insertSelective(authority);

//        if(authority.getAuthType().intValue() == 2){
//            SOperation operation = authorityOperationMapStruct.toEntity(authorityDto);
//            operation.setAuthorityId(authority.getId());
//            operation.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
//            operationMapper.insertSelective(operation);
//        }else if(authority.getAuthType().intValue() == 3){
//            //添加数据权限
//            DataFilterRule rule = authorityDataRuleMapStruct.toEntity(authorityDto);
//            rule.setAuthorityId(authority.getId());
//            dataFilterRuleMapper.insertSelective(rule);
//        }else{
//
//        }

        return count;
    }

    public int update(AuthorityDto authorityDto) {
        if (logger.isInfoEnabled()) {
            logger.info("更新权限:[权限信息:{}]", authorityDto);
        }

        Authority authority = authorityMapStruct.toEntity(authorityDto);
        if (2 == authority.getAuthType()) {
            SOperation operation = authorityOperationMapStruct.toEntity(authorityDto);
            operationMapper.updateByAuthorityId(operation);
        } else if (3 == authority.getAuthType()) {
            DataFilterRule dataFilterRule = authorityDataRuleMapStruct.toEntity(authorityDto);
            dataFilterRuleMapper.updateByAuthorityIdSelective(dataFilterRule);
        } else {

        }

        int num = authorityMapper.updateByIdSelective(authority);
        return num;
    }

    public int deleteByAuthorityId(int authorityId, int authType) {

        if (1 == authType) {
            //菜单权限
            return deleteMenuAuthorityById(authorityId);
        } else if (2 == authType) {
            //控件权限
            return deleteControllerAuthorityById(authorityId);
        } else if (3 == authType) {
            //数据权限
            return deleteDataRuleAuthorityById(authorityId);
        }

        return 0;
    }

    private int deleteMenuAuthorityById(int authorityId) {
        List<RoleAuthority> roleAuthorityList = roleAuthorityMapper.selectByAuthorityId(authorityId);
        Preconditions.checkArgument(roleAuthorityList.size() == 0, ApplicationErrorEnum.DEPENDENCY_EXIST);

        List<Authority> childAuthorityList = getChildById(authorityId);
        Preconditions.checkArgument(childAuthorityList.size() == 0, ApplicationErrorEnum.DEPENDENCY_EXIST);

        return authorityMapper.deleteById(authorityId);
    }

    private int deleteControllerAuthorityById(int authorityId) {
        List<RoleAuthority> roleAuthorityList = roleAuthorityMapper.selectByAuthorityId(authorityId);
        Preconditions.checkArgument(roleAuthorityList.size() == 0, ApplicationErrorEnum.DEPENDENCY_EXIST);

        int num = 0;
        operationMapper.deleteByAuthorityId(authorityId);
        num = authorityMapper.deleteById(authorityId);
        return num;
    }

    private int deleteDataRuleAuthorityById(int authorityId) {
        List<RoleAuthority> roleAuthorityList = roleAuthorityMapper.selectByAuthorityId(authorityId);
        Preconditions.checkArgument(roleAuthorityList.size() == 0, ApplicationErrorEnum.DEPENDENCY_EXIST);

        int num = 0;
        dataFilterRuleMapper.deleteByAuthorityId(authorityId);
        num = authorityMapper.deleteById(authorityId);
        return num;
    }

    public int removePhysicalById(int id) {
        if (logger.isInfoEnabled()) {
            logger.info("删除权限:[条件:{}]", id);
        }

        List<Integer> deleteIds = new ArrayList<>();
        deleteIds.add(id);

        List<Authority> authorities = getChildById(id);
        int size = authorities.size();
        for (int i = 0; i < size; i++) {
            Authority authority = authorities.get(i);
            deleteIds.add(authority.getId());

            List<Authority> sAuthorities = getChildById(authority.getId());
            int sSize = sAuthorities.size();
            for (int j = 0; j < sSize; j++) {
                deleteIds.add(sAuthorities.get(j).getId());
            }
        }
        int num = authorityMapper.deleteByIds(deleteIds);
        return num;
    }

    public List<MenuTreeDto> tree(Integer roleId) {

        List<MenuTreeDto> menuTreeDtos = new ArrayList<>();
        List<RoleAuthority> roleAuthorities = roleAuthorityService.listByRoleId(roleId);
        List<Integer> selectIds = roleAuthorities
                .stream()
                .map(RoleAuthority::getAuthorityId)
                .collect(toList());
        //查询一级菜单
        List<Authority> firstAuthorities = this.getFirstLevelAuthorities();
        int fsize = firstAuthorities.size();

        for (int i = 0; i < fsize; i++) {
            Authority fAuthority = firstAuthorities.get(i);
            MenuTreeDto firstParentMenuTemp = new MenuTreeDto();
            firstParentMenuTemp.setId(fAuthority.getId());
            firstParentMenuTemp.setLabel(fAuthority.getChineseName());
            firstParentMenuTemp.setTitle(fAuthority.getChineseName());
            if (selectIds.contains(firstParentMenuTemp.getId())) {
                firstParentMenuTemp.setChecked(true);
            }
            List<Authority> childAuthorities = getChildById(fAuthority.getId());
            int szie = childAuthorities.size();

            for (int j = 0; j < szie; j++) {
                Authority sAuthority = childAuthorities.get(j);
                MenuTreeDto secondParentMenuTemp = new MenuTreeDto();
                secondParentMenuTemp.setId(sAuthority.getId());
                secondParentMenuTemp.setLabel(sAuthority.getChineseName());
                secondParentMenuTemp.setTitle(sAuthority.getChineseName());
                if (selectIds.contains(secondParentMenuTemp.getId())) {
                    secondParentMenuTemp.setChecked(true);
                }
                List<Authority> schildAuthorities = getChildById(sAuthority.getId());

                int tzie = schildAuthorities.size();
                for (int k = 0; k < tzie; k++) {
                    Authority tAuthority = schildAuthorities.get(k);
                    MenuTreeDto threeParentMenuTemp = new MenuTreeDto();
                    threeParentMenuTemp.setId(tAuthority.getId());
                    threeParentMenuTemp.setLabel(tAuthority.getChineseName());
                    threeParentMenuTemp.setTitle(tAuthority.getChineseName());
                    if (selectIds.contains(threeParentMenuTemp.getId())) {
                        threeParentMenuTemp.setChecked(true);
                    }
                    secondParentMenuTemp.getChildren().add(threeParentMenuTemp);
                }
                firstParentMenuTemp.getChildren().add(secondParentMenuTemp);
            }
            menuTreeDtos.add(firstParentMenuTemp);
        }
        return menuTreeDtos;

    }

    public List<MenuTreeDto> tree() {

        List<MenuTreeDto> menuTreeDtos = new ArrayList<>();

        //查询一级菜单
        List<Authority> firstAuthorities = this.getFirstLevelAuthorities();
        int fsize = firstAuthorities.size();

        for (int i = 0; i < fsize; i++) {
            Authority fAuthority = firstAuthorities.get(i);
            MenuTreeDto firstParentMenuTemp = new MenuTreeDto();
            firstParentMenuTemp.setId(fAuthority.getId());
            firstParentMenuTemp.setLabel(fAuthority.getChineseName());
            firstParentMenuTemp.setTitle(fAuthority.getChineseName());
            List<Authority> childAuthorities = getChildById(fAuthority.getId());
            int szie = childAuthorities.size();

            for (int j = 0; j < szie; j++) {
                Authority sAuthority = childAuthorities.get(j);
                MenuTreeDto secondParentMenuTemp = new MenuTreeDto();
                secondParentMenuTemp.setId(sAuthority.getId());
                secondParentMenuTemp.setLabel(sAuthority.getChineseName());
                secondParentMenuTemp.setTitle(sAuthority.getChineseName());
                List<Authority> schildAuthorities = getChildById(sAuthority.getId());

                int tzie = schildAuthorities.size();
                for (int k = 0; k < tzie; k++) {
                    Authority tAuthority = schildAuthorities.get(k);
                    MenuTreeDto threeParentMenuTemp = new MenuTreeDto();
                    threeParentMenuTemp.setId(tAuthority.getId());
                    threeParentMenuTemp.setLabel(tAuthority.getChineseName());
                    threeParentMenuTemp.setTitle(tAuthority.getChineseName());
                    secondParentMenuTemp.getChildren().add(threeParentMenuTemp);
                }
                firstParentMenuTemp.getChildren().add(secondParentMenuTemp);
            }
            menuTreeDtos.add(firstParentMenuTemp);
        }
        return menuTreeDtos;

    }

    public Authority getOneById(Integer id) {
        if (logger.isInfoEnabled()) {
            logger.info("获取权限详细信息:[条件：{}]", id);
        }

        Authority authority = authorityMapper.selectOneById(id);
        return authority;
    }

    private List<Authority> getFirstLevelAuthorities() {
        Authority authority = new Authority();
        authority.setParentId(0);
        authority.setAuthType((byte) 1);
        return authorityMapper.selectByObject(authority);
    }

    private List<Authority> getChildById(Integer id) {
        Authority authority = new Authority();
        authority.setParentId(id);
        authority.setAuthType((byte) 1);
        return authorityMapper.selectByObject(authority);
    }

    public List<SOperationDto> getControlByMenuId(Integer menuId) {

        List<SOperation> operationList = operationMapper.getByMenuId(menuId);
        return operationMapStruct.toDto(operationList);
    }

    public List<DataFilterRuleDto> getDataRuleByMenuId(Integer menuId) {
        DataFilterRule dataFilterRule = new DataFilterRule();
        dataFilterRule.setMenuId(menuId);
        List<DataFilterRule> dataFilterRuleList = dataFilterRuleMapper.selectByObject(dataFilterRule);
        return dataRuleMapStruct.toDto(dataFilterRuleList);
    }

    public Result getAuthByParentId(Integer parentId) {
        List<Authority> authorityList = authorityMapper.getButtonAuthByParentId(parentId);
        return new Result(authorityList);
    }
}
