package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.domain.base.CodeAndName;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 返回类型的一些SQL
 *
 * @author han
 */
@Repository
public interface TypeMapper {

    /**
     * 根据仓库编码返回仓位类型编码和仓位类型名称
     *
     * @param whCode
     * @return
     */
    List<CodeAndName> getBincodeType(@Param("whCode") String whCode);

    /**
     * 根据仓库编码返回货架类型编码和货架类型名称
     *
     * @param whCode
     * @return
     */
    List<CodeAndName> getPodType(@Param("whCode") String whCode);

    /**
     * 根据仓库编码返回库区类型编码和库区类型名称
     *
     * @param whCode 仓库编码
     * @return
     */
    List<CodeAndName> getWhAreaType(@Param("whCode") String whCode);

    /**
     * 返回仓库类型编码和仓库类型名称
     *
     * @return
     */
    List<CodeAndName> getWhType();
}
