package com.wisdom.iwcs.domain.upstream.mes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 与MES系统交互的返回类
 * @author han
 */
@Getter
@Setter
@ToString
public class MesResult {
    /**
     * 成功标识
     */
    public static String OK = "0";
    /**
     * 失败标识
     */
    public static String NG = "1";
    /**
     * 返回值 OK-成功，NG-失败，
     */
    private String code = OK;
    /**
     * 提示信息
     */
    private String message = "操作成功";
    /**
     * 返回请求code
     */
    private String reqCode;

    /**
     * 构造方法
     */
    public MesResult() {
    }

    public MesResult(String reqCode) {
        this.reqCode = reqCode;
    }

    public static String getOK() {
        return OK;
    }

    public String getCode() {
        return code;
    }

    public MesResult(String code, String message) {
        this.code = code;
        this.message = message;

    }

    public MesResult(String code, String message, String reqCode) {
        this.code = code;
        this.message = message;
        this.reqCode = reqCode;
    }
}
