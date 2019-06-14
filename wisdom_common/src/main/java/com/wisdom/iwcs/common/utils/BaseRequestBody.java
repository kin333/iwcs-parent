package com.wisdom.iwcs.common.utils;

/**
 * 请求体基类
 *
 * @author ted
 * @create 2019-03-14 上午9:46
 **/
public class BaseRequestBody {

    private String srcClientCode;
    private String reqCode;
    private String reqClientType;
    private String reqTime;

    public String getSrcClientCode() {
        return srcClientCode;
    }

    public void setSrcClientCode(String srcClientCode) {
        this.srcClientCode = srcClientCode;
    }

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public String getReqClientType() {
        return reqClientType;
    }

    public void setReqClientType(String reqClientType) {
        this.reqClientType = reqClientType;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }
}
