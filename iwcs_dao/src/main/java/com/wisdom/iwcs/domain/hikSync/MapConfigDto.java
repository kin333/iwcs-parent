package com.wisdom.iwcs.domain.hikSync;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/22 10:04
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "MapCfg")
public class MapConfigDto {

    private String MapName;

    private String MapType;

    private String MapQRCode;

    private String Row;

    private String Col;

    private List<PointInfoDto> PointInfo;

    private String PodPosTrust;

    private String ActUnderPod;

    private String XOffset;

    private String YOffset;

    @Override
    public String toString() {
        return "MapConfigDto{" +
                "MapName='" + MapName + '\'' +
                ", MapType='" + MapType + '\'' +
                ", MapQRCode='" + MapQRCode + '\'' +
                ", Row='" + Row + '\'' +
                ", Col='" + Col + '\'' +
                ", PointInfo=" + PointInfo +
                ", PodPosTrust='" + PodPosTrust + '\'' +
                ", ActUnderPod='" + ActUnderPod + '\'' +
                ", XOffset='" + XOffset + '\'' +
                ", YOffset='" + YOffset + '\'' +
                '}';
    }

    public String getMapName() {
        return MapName;
    }

    public void setMapName(String mapName) {
        MapName = mapName;
    }

    public String getMapType() {
        return MapType;
    }

    public void setMapType(String mapType) {
        MapType = mapType;
    }

    public String getMapQRCode() {
        return MapQRCode;
    }

    public void setMapQRCode(String mapQRCode) {
        MapQRCode = mapQRCode;
    }

    public String getRow() {
        return Row;
    }

    public void setRow(String row) {
        Row = row;
    }

    public String getCol() {
        return Col;
    }

    public void setCol(String col) {
        Col = col;
    }

    public List<PointInfoDto> getPointInfo() {
        return PointInfo;
    }

    public void setPointInfo(List<PointInfoDto> pointInfo) {
        PointInfo = pointInfo;
    }

    public String getPodPosTrust() {
        return PodPosTrust;
    }

    public void setPodPosTrust(String podPosTrust) {
        PodPosTrust = podPosTrust;
    }

    public String getActUnderPod() {
        return ActUnderPod;
    }

    public void setActUnderPod(String actUnderPod) {
        ActUnderPod = actUnderPod;
    }

    public String getXOffset() {
        return XOffset;
    }

    public void setXOffset(String XOffset) {
        this.XOffset = XOffset;
    }

    public String getYOffset() {
        return YOffset;
    }

    public void setYOffset(String YOffset) {
        this.YOffset = YOffset;
    }
}
