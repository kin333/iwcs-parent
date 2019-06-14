package com.wisdom.controller.mapstruct.inv;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.inv.dto.InvTaskCondDetailDTO;
import com.wisdom.iwcs.domain.stock.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface InvTaskCondDetailDTOOrStockMapStruct extends EntityMapper<InvTaskCondDetailDTO, Stock> {
}
