package com.wisdom.iwcs.domain.hikSync;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/19 10:19
 */
public class HikReturnPodStraResponse {

    private String code;
    private String message;
    private String reqCode;
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public HikReturnPodStraResponse() {
        this.code = "0";
        this.message = "success!";
    }

    public HikReturnPodStraResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
