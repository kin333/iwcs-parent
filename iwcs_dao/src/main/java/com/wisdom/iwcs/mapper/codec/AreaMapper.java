package com.wisdom.iwcs.mapper.codec;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.codec.Area;

import java.util.List;
import java.util.Map;

public interface AreaMapper extends MyMapperAndIds<Area> {
    List<Area> selectByMap(Map map);

    List<Area> getAreaListByLevel(Integer level);

    List<Area> getAreaListByParentId(Integer parentId);
}