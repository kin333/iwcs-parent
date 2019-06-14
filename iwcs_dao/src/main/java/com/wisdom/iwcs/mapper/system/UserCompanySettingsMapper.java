package com.wisdom.iwcs.mapper.system;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.system.UserCompanySettings;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserCompanySettingsMapper extends MyMapperAndIds<UserCompanySettings>, DeleteLogicMapper<UserCompanySettings> {
    List<UserCompanySettings> selectPage(Map map);

    List<UserCompanySettings> getByCompanyIdAndType(@Param("companyId") Integer companyId, @Param("dutyType") String DutyType);

    UserCompanySettings getByUserIdAndCompanyId(@Param("userId") Integer userId, @Param("companyId") Integer companyId);

    int deleteByUserIdAndCompanyId(@Param("userId") Integer userId, @Param("companyId") Integer companyId);
}