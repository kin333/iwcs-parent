package com.wisdom.iwcs.mapstruct.system;

import com.wisdom.iwcs.domain.system.Authority;
import com.wisdom.iwcs.domain.system.dto.AuthorityDto;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Region and its DTO RegionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AuthorityMapStruct extends EntityMapper<AuthorityDto, Authority> {

    /*default Region fromId(Long id) {
        if (id == null) {
            return null;
        }
        Region region = new Region();
        region.setId(id);
        return region;
    }*/
}
