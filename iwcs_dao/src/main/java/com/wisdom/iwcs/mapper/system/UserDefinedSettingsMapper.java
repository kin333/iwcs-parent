package com.wisdom.iwcs.mapper.system;


import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.system.UserDefinedSettings;
import com.wisdom.iwcs.domain.system.dto.UserDefinedSettingsDto;

import java.util.List;
import java.util.Map;

public interface UserDefinedSettingsMapper extends MyMapperAndIds<UserDefinedSettings>, DeleteLogicMapper<UserDefinedSettings> {
    List<UserDefinedSettings> selectPage(Map map);

    List<UserDefinedSettings> selectByName(UserDefinedSettingsDto userDefinedSettingsDto);
}