package com.wisdom.controller.mapstruct.outstock;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.outstock.OutstockRecordDetail;
import com.wisdom.iwcs.domain.outstock.dto.OutstockRecordDetailDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity OutstockRecordDetail and its DTO OutstockRecordDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OutstockRecordDetailMapStruct extends EntityMapper<OutstockRecordDetailDTO, OutstockRecordDetail> {

}
