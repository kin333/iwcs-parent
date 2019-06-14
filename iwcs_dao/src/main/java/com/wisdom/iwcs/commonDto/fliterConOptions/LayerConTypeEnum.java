package com.wisdom.iwcs.commonDto.fliterConOptions;

import java.util.Arrays;

/**
 * 货架层级库存状态条件类型
 *
 * @author ted
 * @create 2019-03-12 上午10:18
 **/
public enum LayerConTypeEnum {
    /**
     * 层级条件类型
     * specLayers
     * specLayerUp
     * specLayerDown
     * specLayerBetween
     */
    SPEC_LAYERS("specLayers", "指定层级"), SPEC_LAYER_UP("specLayerUp", "指定层级以上"), SPEC_LAYER_DOWN("specLayerDown", "指定层级以下"), SPEC_LAYER_BETWEEN("specLayerBetween", "层级之间");

    LayerConTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static LayerConTypeEnum fromCode(String code) {
        System.out.println("layerCode:----" + code);
        for (LayerConTypeEnum layerConTypeEnum : values()) {
            if (layerConTypeEnum.code.equalsIgnoreCase(code.trim())) {
                return layerConTypeEnum;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + code + ", Allowed values are " + Arrays.toString(values()));

    }
}
