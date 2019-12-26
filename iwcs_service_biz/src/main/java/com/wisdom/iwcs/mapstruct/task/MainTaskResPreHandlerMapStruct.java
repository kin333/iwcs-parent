package com.wisdom.iwcs.mapstruct.task;

import com.wisdom.iwcs.domain.task.MainTaskResPreHandler;
import com.wisdom.iwcs.domain.task.dto.MainTaskResPreHandlerDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity MainTaskResPreHandler and its DTO MainTaskResPreHandlerDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MainTaskResPreHandlerMapStruct extends EntityMapper<MainTaskResPreHandlerDTO, MainTaskResPreHandler> {

}
