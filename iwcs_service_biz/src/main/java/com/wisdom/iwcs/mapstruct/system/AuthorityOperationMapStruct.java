package com.wisdom.iwcs.mapstruct.system;

import com.wisdom.iwcs.domain.system.SOperation;
import com.wisdom.iwcs.domain.system.dto.AuthorityDto;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Mapper for the entity Region and its DTO RegionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AuthorityOperationMapStruct extends EntityMapper<AuthorityDto, SOperation> {

    @Mappings({
            @Mapping(source = "parentId", target = "menuId"),
            @Mapping(source = "chineseName", target = "operationname")
    })
    SOperation toEntity(AuthorityDto dto);


    @Mappings({
            @Mapping(source = "menuId", target = "parentId"),
            @Mapping(source = "operationname", target = "chineseName")
    })
    AuthorityDto toDto(SOperation entity);

}
