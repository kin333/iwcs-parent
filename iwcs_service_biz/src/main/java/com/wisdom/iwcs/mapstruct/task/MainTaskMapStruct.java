package com.wisdom.iwcs.mapstruct.task;


import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.dto.MainTaskDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity MainTask and its DTO MainTaskDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MainTaskMapStruct extends EntityMapper<MainTaskDTO, MainTask> {

}
