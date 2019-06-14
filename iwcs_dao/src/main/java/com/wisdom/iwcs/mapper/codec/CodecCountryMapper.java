package com.wisdom.iwcs.mapper.codec;


import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.codec.CodecCountry;
import com.wisdom.iwcs.domain.codec.dto.CodecCountryDto;

import java.util.List;
import java.util.Map;

public interface CodecCountryMapper extends MyMapperAndIds<CodecCountry>, DeleteLogicMapper<CodecCountry> {
    List<CodecCountryDto> selectPage(Map map);
}