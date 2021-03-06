package com.wisdom.iwcs.mapstruct.outstock;

import com.wisdom.iwcs.domain.outstock.OutstockBizOrder;
import com.wisdom.iwcs.domain.outstock.dto.OutstockBizOrderDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity OutstockBizOrder and its DTO OutstockBizOrderDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OutstockBizOrderMapStruct extends EntityMapper<OutstockBizOrderDTO, OutstockBizOrder> {

}
