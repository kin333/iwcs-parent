package com.wisdom.iwcs.domain.upstream.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 基础的与Mes
 * @author han
 */
@Getter
@Setter
public class MesBaseRequest<T> {
    public MesBaseRequest() {
    }


    /**

     * 请求编码
     */
    private String reqcode;

    public MesBaseRequest(String reqcode, T data) {
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
}
