package com.wisdom.iwcs.domain.hikSync;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/22 10:06
 */
@XmlAccessorType(XmlAccessType.FIELD)

public class TranZoneTypeDto {
    @XmlAttribute(name = "ParkType")
    private String ParkType;

    @Override
    public String toString() {
        return "TranZoneTypeDto{" +
                "ParkType='" + ParkType + '\'' +
                '}';
    }

    public String getParkType() {
        return ParkType;
    }

    public void setParkType(String parkType) {
        ParkType = parkType;
    }
}
