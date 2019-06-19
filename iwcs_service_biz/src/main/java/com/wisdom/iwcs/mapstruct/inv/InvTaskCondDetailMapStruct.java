package com.wisdom.iwcs.mapstruct.inv;

import com.wisdom.iwcs.domain.inv.InvTaskCondDetail;
import com.wisdom.iwcs.domain.inv.dto.InvTaskCondDetailDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InvTaskCondDetail and its DTO InvTaskCondDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InvTaskCondDetailMapStruct extends EntityMapper<InvTaskCondDetailDTO, InvTaskCondDetail> {

}
