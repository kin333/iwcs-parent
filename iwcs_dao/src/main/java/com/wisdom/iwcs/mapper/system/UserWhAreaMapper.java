package com.wisdom.iwcs.mapper.system;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;

import com.wisdom.iwcs.domain.system.SUserWhArea;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWhAreaMapper extends DeleteLogicMapper<SUserWhArea>, MyMapperAndIds<SUserWhArea> {

    int deleteByUserId(Integer userId);

}
