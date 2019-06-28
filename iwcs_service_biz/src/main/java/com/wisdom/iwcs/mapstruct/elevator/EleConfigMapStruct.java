package com.wisdom.iwcs.mapstruct.elevator;

import com.wisdom.iwcs.domain.elevator.EleConfig;
import com.wisdom.iwcs.domain.elevator.dto.EleConfigDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface EleConfigMapStruct extends EntityMapper<EleConfigDTO, EleConfig> {
}
