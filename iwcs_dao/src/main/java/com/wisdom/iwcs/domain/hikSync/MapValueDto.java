package com.wisdom.iwcs.domain.hikSync;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/22 10:06
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MapValueDto {
    @XmlAttribute(name = "areaCode")
    private String areaCode;


    @Override
    public String toString() {
        return "MapValueDto{" +
                "areaCode='" + areaCode + '\'' +
                '}';
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
