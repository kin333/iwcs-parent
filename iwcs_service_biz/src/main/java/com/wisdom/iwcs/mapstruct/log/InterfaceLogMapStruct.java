package com.wisdom.iwcs.mapstruct.log;

import com.wisdom.iwcs.domain.log.InterfaceLog;
import com.wisdom.iwcs.domain.log.dto.InterfaceLogDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity SystemInterfaceLog and its DTO InterfaceLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InterfaceLogMapStruct extends EntityMapper<InterfaceLogDTO, InterfaceLog> {

}
