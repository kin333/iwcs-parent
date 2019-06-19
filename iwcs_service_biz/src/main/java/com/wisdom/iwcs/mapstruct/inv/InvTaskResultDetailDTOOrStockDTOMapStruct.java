package com.wisdom.iwcs.mapstruct.inv;

import com.wisdom.iwcs.domain.inv.dto.InvTaskResultDetailDTO;
import com.wisdom.iwcs.domain.stock.dto.StockDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * @Auther: panzun
 * @Date: 2019-3-25 17:57
 * @Description:
 */

/**
 * Mapper for the entity InvTaskResultDetail and its DTO InvTaskResultDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public
interface InvTaskResultDetailDTOOrStockDTOMapStruct extends EntityMapper<InvTaskResultDetailDTO, StockDTO> {
}
