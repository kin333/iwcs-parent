package com.wisdom.iwcs.mapstruct.base;

import com.wisdom.iwcs.domain.base.BaseWbGroupDetail;
import com.wisdom.iwcs.domain.base.dto.BaseWbGroupDetailDTO;
import com.wisdom.iwcs.mapstruct.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity BaseWbGroupDetail and its DTO BaseWbGroupDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BaseWbGroupDetailMapStruct extends EntityMapper<BaseWbGroupDetailDTO, BaseWbGroupDetail> {

}
