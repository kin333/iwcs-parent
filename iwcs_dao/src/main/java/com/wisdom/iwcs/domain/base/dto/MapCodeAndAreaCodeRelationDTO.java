package com.wisdom.iwcs.domain.base.dto;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/4 16:32
 */
public class MapCodeAndAreaCodeRelationDTO {

    /**
     * 地图编号
     */
    private String mapCode;
    /**
     * 库区编码
     */
    private String areaCode;

    @Override
    public String toString() {
        return "MapCodeAndAreaCodeRelationDTO{" +
                "mapCode='" + mapCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                '}';
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
