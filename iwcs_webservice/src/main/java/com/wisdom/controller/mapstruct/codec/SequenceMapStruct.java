package com.wisdom.controller.mapstruct.codec;


import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.codec.Sequence;
import com.wisdom.iwcs.domain.codec.dto.SequenceDto;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Sequence and its DTO SequenceDto.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SequenceMapStruct extends EntityMapper<SequenceDto, Sequence> {

}
