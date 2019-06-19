package com.wisdom.iwcs.mapstruct.instock;


import com.wisdom.iwcs.domain.instock.InstockRecordDetail;
import com.wisdom.iwcs.domain.instock.dto.InstockRecordDetailDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InstockRecordDetail and its DTO InstockRecordDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InstockRecordDetailMapStruct extends EntityMapper<InstockRecordDetailDTO, InstockRecordDetail> {

}
