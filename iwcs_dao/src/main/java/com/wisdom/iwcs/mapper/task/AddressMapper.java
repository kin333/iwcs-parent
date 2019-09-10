package com.wisdom.iwcs.mapper.task;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.Address;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-09-05 10:51:00.
 */
@Repository
public interface AddressMapper extends MyMapperAndIds<Address> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<Address> selectPage(Map map);

    @Select("select address from address where code = #{code}")
    String selectAddressByCode(String code);
}