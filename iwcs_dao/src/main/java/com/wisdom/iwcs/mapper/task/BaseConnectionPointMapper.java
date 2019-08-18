package com.wisdom.iwcs.mapper.task;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.BaseConnectionPoint;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-07-30 16:51:38.
 */
@Repository
public interface BaseConnectionPointMapper extends DeleteLogicMapper<BaseConnectionPoint>, MyMapperAndIds<BaseConnectionPoint> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<BaseConnectionPoint> selectPage(Map map);

    /**
     * 更加mapCode查询电梯交接点
     * 每个mapCode只能有一个电梯交接点
     */
    @Select("select ber_code from base_connection_point where map_code = #{mapCode}")
    String selectBerCodeByMapCode(String mapCode);
    /**
     * 查询某个楼层的电梯检查点
     * 每个mapCode只能有一个电梯交接点
     */
    @Select("select ber_code from base_connection_point where map_code = #{mapCode} and connection_point_type = '1'")
    String selectCheckBerCodeByMapCode(String mapCode);

    /**
     * 查询线体关联点
     * 线体每个作业点只能有一个关联点(通知线体小车已经搬走货架)
     */
    @Select("select connection_point_code from base_connection_point where ber_code = #{berCode} and map_code = #{mapCode} and connection_point_type = '3'")
    String selectConnectionPointByMapCodeBerCode(@Param("berCode") String berCode, @Param("mapCode") String mapCode);
}