package com.wisdom.iwcs.mapstruct.instock;

import com.wisdom.iwcs.domain.instock.InstockCallStraPri;
import com.wisdom.iwcs.domain.instock.dto.InstockCallStraPriDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InstockCallStraPri and its DTO InstockCallStraPriDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstockCallStraPriMapStruct extends EntityMapper<InstockCallStraPriDTO, InstockCallStraPri> {

}
