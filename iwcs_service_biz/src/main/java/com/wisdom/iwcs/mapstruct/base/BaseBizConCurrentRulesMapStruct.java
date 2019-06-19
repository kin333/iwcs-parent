package com.wisdom.iwcs.mapstruct.base;

import com.wisdom.iwcs.domain.base.BaseBizConCurrentRules;
import com.wisdom.iwcs.domain.base.dto.BaseBizConCurrentRulesDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseBizConCurrentRules and its DTO BaseBizConCurrentRulesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseBizConCurrentRulesMapStruct extends EntityMapper<BaseBizConCurrentRulesDTO, BaseBizConCurrentRules> {

}
