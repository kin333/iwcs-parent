package com.wisdom.controller.test;

import lombok.Getter;
import lombok.Setter;


/**
 * 基础的与Mes
 * @author han
 */
@Getter
@Setter
public class TestMesBaseRequest<T> {
    public TestMesBaseRequest() {
    }


    /**

     * 请求编码
     */
    private String reqcode;

    public TestMesBaseRequest(String reqcode, T data) {
        this.reqcode = reqcode;
        this.data = data;
    }

    public String getReqcode() {
        return reqcode;
    }

    public void setReqcode(String reqcode) {
        this.reqcode = reqcode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 请求数据

     */
    private T data;

    private String taskCode;
    private String supplyLoadWb;
    private Integer supplyLoadNum;
    private String emptyRecyleWb;
    private Integer emptyRecyleNum;
    private String supplyUnLoadWb;
    private Integer supplyUnLoadNum;
    private String currentWb;
    private String agvCode;
    private String taskSta;
}
