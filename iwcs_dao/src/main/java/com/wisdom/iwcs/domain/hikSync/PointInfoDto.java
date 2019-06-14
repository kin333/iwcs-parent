package com.wisdom.iwcs.domain.hikSync;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/22 10:05
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PointInfoDto {

    private String id;

    private String xpos;

    private String ypos;


    private MapValueDto value;

    private String RoadProperty;

    private String Rot;

    private String alldirRot;

    private String Dir;

    private String allDir;

    private TranZoneTypeDto TranZoneType;

    private NeighbInfoDto NeighbInfo;

    @Override
    public String toString() {
        return "PointInfoDto{" +
                "id='" + id + '\'' +
                ", xpos='" + xpos + '\'' +
                ", ypos='" + ypos + '\'' +
                ", value=" + value +
                ", RoadProperty='" + RoadProperty + '\'' +
                ", Rot='" + Rot + '\'' +
                ", alldirRot='" + alldirRot + '\'' +
                ", Dir='" + Dir + '\'' +
                ", allDir='" + allDir + '\'' +
                ", TranZoneType=" + TranZoneType +
                ", NeighbInfo=" + NeighbInfo +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXpos() {
        return xpos;
    }

    public void setXpos(String xpos) {
        this.xpos = xpos;
    }

    public String getYpos() {
        return ypos;
    }

    public void setYpos(String ypos) {
        this.ypos = ypos;
    }

    public MapValueDto getValue() {
        return value;
    }

    public void setValue(MapValueDto value) {
        this.value = value;
    }

    public String getRoadProperty() {
        return RoadProperty;
    }

    public void setRoadProperty(String roadProperty) {
        RoadProperty = roadProperty;
    }

    public String getRot() {
        return Rot;
    }

    public void setRot(String rot) {
        Rot = rot;
    }

    public String getAlldirRot() {
        return alldirRot;
    }

    public void setAlldirRot(String alldirRot) {
        this.alldirRot = alldirRot;
    }

    public String getDir() {
        return Dir;
    }

    public void setDir(String dir) {
        Dir = dir;
    }

    public String getAllDir() {
        return allDir;
    }

    public void setAllDir(String allDir) {
        this.allDir = allDir;
    }

    public TranZoneTypeDto getTranZoneType() {
        return TranZoneType;
    }

    public void setTranZoneType(TranZoneTypeDto tranZoneType) {
        TranZoneType = tranZoneType;
    }

    public NeighbInfoDto getNeighbInfo() {
        return NeighbInfo;
    }

    public void setNeighbInfo(NeighbInfoDto neighbInfo) {
        NeighbInfo = neighbInfo;
    }
}
