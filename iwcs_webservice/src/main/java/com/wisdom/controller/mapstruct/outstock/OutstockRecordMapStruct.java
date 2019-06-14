package com.wisdom.controller.mapstruct.outstock;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.outstock.OutstockRecord;
import com.wisdom.iwcs.domain.outstock.dto.OutstockRecordDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity OutstockRecord and its DTO OutstockRecordDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OutstockRecordMapStruct extends EntityMapper<OutstockRecordDTO, OutstockRecord> {

}
