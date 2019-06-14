package com.wisdom.controller.mapstruct.quartz;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.quartz.QuartzJobLog;
import com.wisdom.iwcs.domain.quartz.dto.QuartzJobLogDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity QuartzJobLog and its DTO QuartzJobLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuartzJobLogMapStruct extends EntityMapper<QuartzJobLogDTO, QuartzJobLog> {

}
