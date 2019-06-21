package com.wisdom.iwcs.mapstruct.task;


import com.wisdom.iwcs.domain.task.TsTaskRel;
import com.wisdom.iwcs.domain.task.dto.TsTaskRelDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity TsTaskRel and its DTO TsTaskRelDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TsTaskRelMapStruct extends EntityMapper<TsTaskRelDTO, TsTaskRel> {

}
