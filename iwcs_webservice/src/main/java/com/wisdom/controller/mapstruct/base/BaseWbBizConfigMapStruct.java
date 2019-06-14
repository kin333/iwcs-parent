package com.wisdom.controller.mapstruct.base;

import com.wisdom.controller.mapstruct.EntityMapper;
import com.wisdom.iwcs.domain.base.BaseWbBizConfig;
import com.wisdom.iwcs.domain.base.dto.BaseWbBizConfigDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseWbBizConfig and its DTO BaseWbBizConfigDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseWbBizConfigMapStruct extends EntityMapper<BaseWbBizConfigDTO, BaseWbBizConfig> {

}
