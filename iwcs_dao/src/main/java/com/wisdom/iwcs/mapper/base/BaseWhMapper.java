package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BaseWh;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 17:04:55.
 */
@Repository
public interface BaseWhMapper extends DeleteLogicMapper<BaseWh>, MyMapperAndIds<BaseWh> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BaseWh> selectPage(Map map);

    /**
     * 根据仓库代码删除仓库信息
     *
     * @param whCode
     * @return
     */
    int deleteByWhCode(String whCode);

    BaseWh selectByWhCodeAndValidFlagAndDeleteFlag(@Param("whCode") String whCode, @Param("validFlag") Integer validFlag, @Param("deleteFlag") Integer deleteFlag);
}