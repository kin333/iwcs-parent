package com.wisdom.iwcs.mapper.codec;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.codec.BusinessCode;
import com.wisdom.iwcs.domain.codec.dto.BusinessCodeDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BusinessCodeMapper extends MyMapperAndIds<BusinessCode>, DeleteLogicMapper<BusinessCode> {
    List<BusinessCode> selectPage(Map map);

    List<BusinessCodeDto> selectDTOByMap(Map map);

    List<BusinessCode> getAllCodeByType(String codeType);

    List<BusinessCode> searchFromAll(Map map);

    BusinessCode getByCodeAndType(@Param("code") String code, @Param("codeType") String CodeType);

    List<BusinessCode> getByCodeListAndType(@Param("list") List<String> codeList, @Param("codeType") String CodeType);

    List<BusinessCode> getAllUseBusinessCode(Map map);

    Integer updateRouteByCountryCode(BusinessCode businessCode);
}