package com.wisdom.iwcs.mapper.system;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.domain.system.CustomVisit;
import tk.mybatis.mapper.common.Mapper;

public interface CustomVisitMapper extends Mapper<CustomVisit>, DeleteLogicMapper<CustomVisit> {
}