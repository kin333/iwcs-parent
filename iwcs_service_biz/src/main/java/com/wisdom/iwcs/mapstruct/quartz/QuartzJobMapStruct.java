package com.wisdom.iwcs.mapstruct.quartz;

import com.wisdom.iwcs.domain.quartz.QuartzJob;
import com.wisdom.iwcs.domain.quartz.dto.QuartzJobDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity QuartzJob and its DTO QuartzJobDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuartzJobMapStruct extends EntityMapper<QuartzJobDTO, QuartzJob> {

}
