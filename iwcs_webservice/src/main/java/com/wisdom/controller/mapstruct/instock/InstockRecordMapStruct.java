package com.wisdom.controller.mapstruct.instock;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.instock.InstockRecord;
import com.wisdom.iwcs.domain.instock.dto.InstockRecordDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InstockRecord and its DTO InstockRecordDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstockRecordMapStruct extends EntityMapper<InstockRecordDTO, InstockRecord> {

}
