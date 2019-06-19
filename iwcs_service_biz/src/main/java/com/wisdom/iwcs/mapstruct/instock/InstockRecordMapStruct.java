package com.wisdom.iwcs.mapstruct.instock;

import com.wisdom.iwcs.domain.instock.InstockRecord;
import com.wisdom.iwcs.domain.instock.dto.InstockRecordDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InstockRecord and its DTO InstockRecordDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstockRecordMapStruct extends EntityMapper<InstockRecordDTO, InstockRecord> {

}
