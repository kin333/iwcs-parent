package com.wisdom.service.codec.extface;

import com.wisdom.iwcs.domain.codec.dto.BusinessCodeDto;

import java.util.List;

public interface CodecInterface {
    /**
     * 尺寸箱型
     */
    List<BusinessCodeDto> getContainerModels();

    /**
     * 包装类型
     */
    List<BusinessCodeDto> getPackageTypes();
}
