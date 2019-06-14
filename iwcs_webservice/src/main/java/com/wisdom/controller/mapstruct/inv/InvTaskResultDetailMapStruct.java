package com.wisdom.controller.mapstruct.inv;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.inv.InvTaskResultDetail;
import com.wisdom.iwcs.domain.inv.dto.InvTaskResultDetailDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InvTaskResultDetail and its DTO InvTaskResultDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InvTaskResultDetailMapStruct extends EntityMapper<InvTaskResultDetailDTO, InvTaskResultDetail> {

}
