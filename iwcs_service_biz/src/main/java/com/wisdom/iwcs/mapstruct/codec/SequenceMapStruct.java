package com.wisdom.iwcs.mapstruct.codec;


import com.wisdom.iwcs.domain.codec.Sequence;
import com.wisdom.iwcs.domain.codec.dto.SequenceDto;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Sequence and its DTO SequenceDto.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SequenceMapStruct extends EntityMapper<SequenceDto, Sequence> {

}
