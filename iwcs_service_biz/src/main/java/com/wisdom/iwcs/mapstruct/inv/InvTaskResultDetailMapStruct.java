package com.wisdom.iwcs.mapstruct.inv;

import com.wisdom.iwcs.domain.inv.InvTaskResultDetail;
import com.wisdom.iwcs.domain.inv.dto.InvTaskResultDetailDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity InvTaskResultDetail and its DTO InvTaskResultDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InvTaskResultDetailMapStruct extends EntityMapper<InvTaskResultDetailDTO, InvTaskResultDetail> {

}
