package com.wisdom.iwcs.commonDto.fliterConOptions;

import java.beans.PropertyEditorSupport;

/**
 * 层级库存状态转换类
 *
 * @author ted
 * @create 2019-03-13 下午1:53
 **/

public class LayerConTypeConverter extends PropertyEditorSupport {
    @Override
    public void setAsText(final String text) throws IllegalArgumentException {
        setValue(LayerConTypeEnum.fromCode(text));
    }

    @Override
    public String getAsText() {
        LayerConTypeEnum layerConTypeEnum = (LayerConTypeEnum) getValue();
        return layerConTypeEnum == null ? "" : layerConTypeEnum.getCode();
    }
}
