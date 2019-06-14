package com.wisdom.iwcs.mapper.codec;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.codec.ParameterSetting;

import java.util.List;
import java.util.Map;

public interface ParameterSettingMapper extends MyMapperAndIds<ParameterSetting>, DeleteLogicMapper<ParameterSetting> {
    List<ParameterSetting> selectPage(Map map);
}