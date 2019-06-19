package com.wisdom.iwcs.mapstruct.base;

import com.wisdom.iwcs.domain.base.BaseWbBizConfig;
import com.wisdom.iwcs.domain.base.dto.BaseWbBizConfigDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseWbBizConfig and its DTO BaseWbBizConfigDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseWbBizConfigMapStruct extends EntityMapper<BaseWbBizConfigDTO, BaseWbBizConfig> {

}
