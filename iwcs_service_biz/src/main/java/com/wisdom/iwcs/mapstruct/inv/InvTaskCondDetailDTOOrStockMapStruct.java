package com.wisdom.iwcs.mapstruct.inv;

import com.wisdom.iwcs.domain.inv.dto.InvTaskCondDetailDTO;
import com.wisdom.iwcs.domain.stock.Stock;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface InvTaskCondDetailDTOOrStockMapStruct extends EntityMapper<InvTaskCondDetailDTO, Stock> {
}
