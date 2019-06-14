package com.wisdom.controller.mapstruct.instock;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.instock.InstockCallStraPri;
import com.wisdom.iwcs.domain.instock.dto.InstockCallStraPriDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InstockCallStraPri and its DTO InstockCallStraPriDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstockCallStraPriMapStruct extends EntityMapper<InstockCallStraPriDTO, InstockCallStraPri> {

}
