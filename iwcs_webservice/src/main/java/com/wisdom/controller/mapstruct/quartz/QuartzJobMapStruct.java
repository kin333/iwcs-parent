package com.wisdom.controller.mapstruct.quartz;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.quartz.QuartzJob;
import com.wisdom.iwcs.domain.quartz.dto.QuartzJobDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity QuartzJob and its DTO QuartzJobDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuartzJobMapStruct extends EntityMapper<QuartzJobDTO, QuartzJob> {

}
