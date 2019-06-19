package com.wisdom.iwcs.mapstruct.quartz;

import com.wisdom.iwcs.domain.quartz.QuartzJobLog;
import com.wisdom.iwcs.domain.quartz.dto.QuartzJobLogDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity QuartzJobLog and its DTO QuartzJobLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuartzJobLogMapStruct extends EntityMapper<QuartzJobLogDTO, QuartzJobLog> {

}
