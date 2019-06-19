package com.wisdom.iwcs.mapstruct.inv;

import com.wisdom.iwcs.domain.inv.dto.InvTaskCondDetailDTO;
import com.wisdom.iwcs.domain.inv.dto.InvTaskResultDetailDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * @Auther: panzun
 * @Date: 2019-3-21 14:27
 * @Description:
 */
@Mapper(componentModel = "spring", uses = {})
public
interface InvTaskCondDTOandResultDetailMapStruct extends EntityMapper<InvTaskCondDetailDTO, InvTaskResultDetailDTO> {
}
