package com.wisdom.iwcs.domain.hikSync;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/22 10:07
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class NeighbInfoDto {
    private List<String> id;

    private List<String> distance;

    private List<String> Rever;

    private List<String> Speed;

    private List<String> Ultrasonic;

    private List<String> LeftWidth;

    private List<String> RightWidth;

    private List<String> robottype;

    private List<String> PodDir;

    private List<String> StartDir;

    private List<String> StopDir;

    private List<String> AlarmVoice;

    @Override
    public String toString() {
        return "NeighbInfoDto{" +
                "id=" + id +
                ", distance=" + distance +
                ", Rever=" + Rever +
                ", Speed=" + Speed +
                ", Ultrasonic=" + Ultrasonic +
                ", LeftWidth=" + LeftWidth +
                ", RightWidth=" + RightWidth +
                ", robottype=" + robottype +
                ", PodDir=" + PodDir +
                ", StartDir=" + StartDir +
                ", StopDir=" + StopDir +
                ", AlarmVoice=" + AlarmVoice +
                '}';
    }

    public List<String> getId() {
        return id;
    }

    public void setId(List<String> id) {
        this.id = id;
    }

    public List<String> getDistance() {
        return distance;
    }

    public void setDistance(List<String> distance) {
        this.distance = distance;
    }

    public List<String> getRever() {
        return Rever;
    }

    public void setRever(List<String> rever) {
        Rever = rever;
    }

    public List<String> getSpeed() {
        return Speed;
    }

    public void setSpeed(List<String> speed) {
        Speed = speed;
    }

    public List<String> getUltrasonic() {
        return Ultrasonic;
    }

    public void setUltrasonic(List<String> ultrasonic) {
        Ultrasonic = ultrasonic;
    }

    public List<String> getLeftWidth() {
        return LeftWidth;
    }

    public void setLeftWidth(List<String> leftWidth) {
        LeftWidth = leftWidth;
    }

    public List<String> getRightWidth() {
        return RightWidth;
    }

    public void setRightWidth(List<String> rightWidth) {
        RightWidth = rightWidth;
    }

    public List<String> getRobottype() {
        return robottype;
    }

    public void setRobottype(List<String> robottype) {
        this.robottype = robottype;
    }

    public List<String> getPodDir() {
        return PodDir;
    }

    public void setPodDir(List<String> podDir) {
        PodDir = podDir;
    }

    public List<String> getStartDir() {
        return StartDir;
    }

    public void setStartDir(List<String> startDir) {
        StartDir = startDir;
    }

    public List<String> getStopDir() {
        return StopDir;
    }

    public void setStopDir(List<String> stopDir) {
        StopDir = stopDir;
    }

    public List<String> getAlarmVoice() {
        return AlarmVoice;
    }

    public void setAlarmVoice(List<String> alarmVoice) {
        AlarmVoice = alarmVoice;
    }
}
