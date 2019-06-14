package com.wisdom.controller.mapstruct.system;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.system.DataFilterRule;
import com.wisdom.iwcs.domain.system.dto.AuthorityDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Mapper for the entity Region and its DTO RegionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AuthorityDataRuleMapStruct extends EntityMapper<AuthorityDto, DataFilterRule> {


    @Mappings({
            @Mapping(source = "parentId", target = "menuId"),
            @Mapping(source = "chineseName", target = "ruleName")
    })
    DataFilterRule toEntity(AuthorityDto dto);


    @Mappings({
            @Mapping(source = "menuId", target = "parentId"),
            @Mapping(source = "ruleName", target = "chineseName")
    })
    AuthorityDto toDto(DataFilterRule entity);

}
