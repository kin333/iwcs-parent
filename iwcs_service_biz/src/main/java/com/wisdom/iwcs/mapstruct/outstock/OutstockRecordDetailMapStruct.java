package com.wisdom.iwcs.mapstruct.outstock;

import com.wisdom.iwcs.domain.outstock.OutstockRecordDetail;
import com.wisdom.iwcs.domain.outstock.dto.OutstockRecordDetailDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity OutstockRecordDetail and its DTO OutstockRecordDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OutstockRecordDetailMapStruct extends EntityMapper<OutstockRecordDetailDTO, OutstockRecordDetail> {

}
