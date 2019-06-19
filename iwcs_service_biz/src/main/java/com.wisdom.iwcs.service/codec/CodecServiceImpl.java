package com.wisdom.iwcs.service.codec;

import com.wisdom.iwcs.domain.codec.BusinessCode;
import com.wisdom.iwcs.domain.codec.BusinessCodeEnum;
import com.wisdom.iwcs.domain.codec.dto.BusinessCodeDto;
import com.wisdom.iwcs.mapper.codec.BusinessCodeMapper;
import com.wisdom.iwcs.mapstruct.codec.BusinessCodeMapStruct;
import com.wisdom.iwcs.service.codec.extface.CodecInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodecServiceImpl implements CodecInterface {
    private final Logger logger = LoggerFactory.getLogger(CodecServiceImpl.class);

    @Autowired
    BusinessCodeMapper businessCodeMapper;
    @Autowired
    BusinessCodeMapStruct businessCodeMapStruct;

    /**
     * 尺寸箱型
     *
     * @return
     */
    @Override
    public List<BusinessCodeDto> getContainerModels() {
        return this.getAllCodeByType(BusinessCodeEnum.ContainerModel.getCode());
    }

    /**
     * 包装类型
     *
     * @return
     */
    @Override
    public List<BusinessCodeDto> getPackageTypes() {
        return this.getAllCodeByType(BusinessCodeEnum.PackageType.getCode());
    }


    /**
     * 不让外部调用
     *
     * @param type
     * @return
     */
    private List<BusinessCodeDto> getAllCodeByType(String type) {
        List<BusinessCode> list = businessCodeMapper.getAllCodeByType(type);
        return businessCodeMapStruct.toDto(list);
    }
}
