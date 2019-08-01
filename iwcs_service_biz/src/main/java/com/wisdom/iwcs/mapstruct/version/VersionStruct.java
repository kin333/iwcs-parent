package com.wisdom.iwcs.mapstruct.version;


import com.wisdom.iwcs.domain.version.Version;
import com.wisdom.iwcs.domain.version.VersionDto;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface VersionStruct extends EntityMapper<VersionDto, Version> {

}
