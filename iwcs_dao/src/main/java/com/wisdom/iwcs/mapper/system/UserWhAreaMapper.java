package com.wisdom.iwcs.mapper.system;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;

import com.wisdom.iwcs.domain.system.SUserWhArea;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserWhAreaMapper extends  MyMapperAndIds<SUserWhArea> {

    int deleteByUserId(Integer userId);

    List<SUserWhArea> selectUserWhAreaByUserId(Integer userId);

}
