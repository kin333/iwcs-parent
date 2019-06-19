package com.wisdom.iwcs.mapstruct.system;

import com.wisdom.iwcs.domain.system.DataFilterRule;
import com.wisdom.iwcs.domain.system.dto.DataFilterRuleDto;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Region and its DTO RegionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DataRuleMapStruct extends EntityMapper<DataFilterRuleDto, DataFilterRule> {

}
