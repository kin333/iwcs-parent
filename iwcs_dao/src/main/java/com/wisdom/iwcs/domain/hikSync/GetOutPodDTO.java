package com.wisdom.iwcs.domain.hikSync;

import com.wisdom.iwcs.domain.outstock.dto.OutstockCalPodResultDTO;

import java.util.List;

/**
 * 货架出库请求，内部用
 *
 * @Author: cecilia.yang
 * @Date: 2019/2/27 9:35
 */
public class GetOutPodDTO {
    /**
     * 必填，出库货架号
     */
    private List<String> bincodes;
    /**
     * 必填，出库点位号，如101，201等
     */
    private String wbCode;
    /**
     * 必填，任务类型，AgvTaskConstants.java中的任务类型常量（AgvTaskTypeConstants）
     */
    private String taskType;
    /**
     * 选填，呼叫客户端类型，0手持，1PC端，2上游系统
     */
    private String srcClientType;
    /**
     * 选填，呼叫编号,上游系统指定
     */
    private String srcReqCode;
    /**
     * 选填，呼叫客户端编号，手持设备编号等
     */
    private String srcClientCode;
    /**
     * 选填，呼叫用户编号，登陆用户等
     */
    private String srcUserCode;
    /**
     * 选填，出库/盘点用
     * 出库：上游未按照订单号呼叫时，IWCS自动生成虚拟单据，虚拟单据号与任务绑定，后续操作时追踪单号用
     * 盘点：单号
     */
    private String bizOrderCode;
    /**
     * 确认后是否循环补充，0：否，1：循环
     */
    private String looplb;
    /**
     * 选填
     */
    private String bizItem1;
    /**
     * 选填
     */
    private String bizItem2;
    /**
     * 选填
     */
    private String bizItem3;
    /**
     * 选填
     */
    private String bizItem4;
    /**
     * 选填
     */
    private String bizItem5;
    /**
     * 任务进度
     */
    private List<OutstockCalPodResultDTO> taskProcess;


    @Override
    public String toString() {
        return "GetOutPodDTO{" +
                "bincodes=" + bincodes +
                ", wbCode='" + wbCode + '\'' +
                ", taskType='" + taskType + '\'' +
                ", srcClientType='" + srcClientType + '\'' +
                ", srcReqCode='" + srcReqCode + '\'' +
                ", srcClientCode='" + srcClientCode + '\'' +
                ", srcUserCode='" + srcUserCode + '\'' +
                ", bizOrderCode='" + bizOrderCode + '\'' +
                ", looplb='" + looplb + '\'' +
                ", bizItem1='" + bizItem1 + '\'' +
                ", bizItem2='" + bizItem2 + '\'' +
                ", bizItem3='" + bizItem3 + '\'' +
                ", bizItem4='" + bizItem4 + '\'' +
                ", bizItem5='" + bizItem5 + '\'' +
                ", taskProcess=" + taskProcess +
                '}';
    }

    public List<OutstockCalPodResultDTO> getTaskProcess() {
        return taskProcess;
    }

    public void setTaskProcess(List<OutstockCalPodResultDTO> taskProcess) {
        this.taskProcess = taskProcess;
    }

    public String getSrcClientType() {
        return srcClientType;
    }

    public void setSrcClientType(String srcClientType) {
        this.srcClientType = srcClientType;
    }

    public String getSrcReqCode() {
        return srcReqCode;
    }

    public void setSrcReqCode(String srcReqCode) {
        this.srcReqCode = srcReqCode;
    }

    public String getSrcClientCode() {
        return srcClientCode;
    }

    public void setSrcClientCode(String srcClientCode) {
        this.srcClientCode = srcClientCode;
    }

    public String getSrcUserCode() {
        return srcUserCode;
    }

    public void setSrcUserCode(String srcUserCode) {
        this.srcUserCode = srcUserCode;
    }

    public String getBizOrderCode() {
        return bizOrderCode;
    }

    public void setBizOrderCode(String bizOrderCode) {
        this.bizOrderCode = bizOrderCode;
    }

    public String getLooplb() {
        return looplb;
    }

    public void setLooplb(String looplb) {
        this.looplb = looplb;
    }

    public String getBizItem1() {
        return bizItem1;
    }

    public void setBizItem1(String bizItem1) {
        this.bizItem1 = bizItem1;
    }

    public String getBizItem2() {
        return bizItem2;
    }

    public void setBizItem2(String bizItem2) {
        this.bizItem2 = bizItem2;
    }

    public String getBizItem3() {
        return bizItem3;
    }

    public void setBizItem3(String bizItem3) {
        this.bizItem3 = bizItem3;
    }

    public String getBizItem4() {
        return bizItem4;
    }

    public void setBizItem4(String bizItem4) {
        this.bizItem4 = bizItem4;
    }

    public String getBizItem5() {
        return bizItem5;
    }

    public void setBizItem5(String bizItem5) {
        this.bizItem5 = bizItem5;
    }

    public List<String> getBincodes() {
        return bincodes;
    }

    public void setBincodes(List<String> bincodes) {
        this.bincodes = bincodes;
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
}
