package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BaseWhArea;
import com.wisdom.iwcs.domain.system.SUserWhArea;
import com.wisdom.iwcs.domain.system.dto.LoginDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 17:05:37.
 */
@Repository
public interface BaseWhAreaMapper extends DeleteLogicMapper<BaseWhArea>, MyMapperAndIds<BaseWhArea> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BaseWhArea> selectPage(Map map);

    Integer selectCountByWhCodeAndDeleteFlag(@Param("whCode") String whCode, @Param("deleteFlag") Integer deleteFlag);

    /**
     * 根据库区编码获取库区信息
     *
     * @param requestAreaCode
     * @param deleteFlag
     * @return
     */
    BaseWhArea selectByAreaCodeAndDeleteFlag(@Param("areaCode") String requestAreaCode, @Param("deleteFlag") Integer deleteFlag);

    SUserWhArea selectAreaUser(@Param("areaCode") String areaCode, @Param("userId") Integer userId);

}