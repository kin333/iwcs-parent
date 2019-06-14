package com.wisdom.iwcs.mapper.system;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.system.TableColumnsSettings;
import com.wisdom.iwcs.domain.system.dto.TableColumnsSettingsDto;

import java.util.List;
import java.util.Map;

public interface TableColumnsSettingsMapper extends MyMapperAndIds<TableColumnsSettings>, DeleteLogicMapper<TableColumnsSettings> {
    List<TableColumnsSettings> selectPage(Map map);

    Integer deleteByTableName(TableColumnsSettingsDto tableColumnsSettingsDto);

    Integer deleteByLayout(TableColumnsSettingsDto tableColumnsSettingsDto);
}