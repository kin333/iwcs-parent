package com.wisdom.iwcs.domain.instock.dto.instockrequest;


/**
 * 入库呼叫
 *
 * @author george
 */
public class BeginUnloadRequestDTO {

    /**
     * 请求编号,可自己生成
     */
    private String reqcode;

    /**
     * 点位，必填项
     */
    private String wbCode;

    /**
     * 库区
     */
    private String areaCode;

    /**
     * 货架数量，选填项
     */
    private Integer podNum;

    /**
     * 操作员，选填项
     */
    private String userId;

    /**
     * 选填，呼叫客户端编号，手持设备编号等
     */
    private String srcClientCode;

    /**
     * 入库确认后是否循环补充，0：否，1：循环
     */
    private String looplb;

    /**
     * 入库呼叫策略code
     */
    private String straCode;

    /**
     * 入库呼叫策略参数
     */
    private InstockCallStraParamDetail instockCallStraParamDetail;

    /**
     * 呼叫预留属性
     */
    private String podProp1;
    /**
     * 呼叫预留属性
     */
    private String podProp2;
    /**
     * 呼叫预留属性
     */
    private String podProp3;
    /**
     * 呼叫预留属性
     */
    private String podProp4;
    /**
     * 呼叫预留属性
     */
    private String podProp5;

    @Override
    public String toString() {
        return "BeginUnloadRequestDTO{" +
                "reqcode='" + reqcode + '\'' +
                ", wbCode='" + wbCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", podNum=" + podNum +
                ", userId='" + userId + '\'' +
                ", srcClientCode='" + srcClientCode + '\'' +
                ", looplb='" + looplb + '\'' +
                ", straCode='" + straCode + '\'' +
                ", instockCallStraParamDetail=" + instockCallStraParamDetail +
                ", podProp1='" + podProp1 + '\'' +
                ", podProp2='" + podProp2 + '\'' +
                ", podProp3='" + podProp3 + '\'' +
                ", podProp4='" + podProp4 + '\'' +
                ", podProp5='" + podProp5 + '\'' +
                '}';
    }

    public String getReqcode() {
        return reqcode;
    }

    public void setReqcode(String reqcode) {
        this.reqcode = reqcode;
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSrcClientCode() {
        return srcClientCode;
    }

    public void setSrcClientCode(String srcClientCode) {
        this.srcClientCode = srcClientCode;
    }

    public Integer getPodNum() {
        return podNum;
    }

    public void setPodNum(Integer podNum) {
        this.podNum = podNum;
    }

    public String getLooplb() {
        return looplb;
    }

    public void setLooplb(String looplb) {
        this.looplb = looplb;
    }

    public String getStraCode() {
        return straCode;
    }

    public void setStraCode(String straCode) {
        this.straCode = straCode;
    }

    public InstockCallStraParamDetail getInstockCallStraParamDetail() {
        return instockCallStraParamDetail;
    }

    public void setInstockCallStraParamDetail(InstockCallStraParamDetail instockCallStraParamDetail) {
        this.instockCallStraParamDetail = instockCallStraParamDetail;
    }

    public String getPodProp1() {
        return podProp1;
    }

    public void setPodProp1(String podProp1) {
        this.podProp1 = podProp1;
    }

    public String getPodProp2() {
        return podProp2;
    }

    public void setPodProp2(String podProp2) {
        this.podProp2 = podProp2;
    }

    public String getPodProp3() {
        return podProp3;
    }

    public void setPodProp3(String podProp3) {
        this.podProp3 = podProp3;
    }

    public String getPodProp4() {
        return podProp4;
    }

    public void setPodProp4(String podProp4) {
        this.podProp4 = podProp4;
    }

    public String getPodProp5() {
        return podProp5;
    }

    public void setPodProp5(String podProp5) {
        this.podProp5 = podProp5;
    }
}
