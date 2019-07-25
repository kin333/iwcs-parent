package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.LineBody;
import com.wisdom.iwcs.domain.task.dto.LineBodyDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity LineBody and its DTO LineBodyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LineBodyMapStruct extends EntityMapper<LineBodyDTO, LineBody> {

}
