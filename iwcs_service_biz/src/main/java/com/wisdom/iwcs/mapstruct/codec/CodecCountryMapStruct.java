package com.wisdom.iwcs.mapstruct.codec;


import com.wisdom.iwcs.domain.codec.CodecCountry;
import com.wisdom.iwcs.domain.codec.dto.CodecCountryDto;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity CodecCountry and its DTO CodecCountryDto.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CodecCountryMapStruct extends EntityMapper<CodecCountryDto, CodecCountry> {

}
