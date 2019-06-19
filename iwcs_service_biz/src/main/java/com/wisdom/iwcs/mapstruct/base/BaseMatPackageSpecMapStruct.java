package com.wisdom.iwcs.mapstruct.base;


import com.wisdom.iwcs.domain.base.BaseMatPackageSpec;
import com.wisdom.iwcs.domain.base.dto.BaseMatPackageSpecDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseMatPackageSpec and its DTO BaseMatPackageSpecDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseMatPackageSpecMapStruct extends EntityMapper<BaseMatPackageSpecDTO, BaseMatPackageSpec> {

}
