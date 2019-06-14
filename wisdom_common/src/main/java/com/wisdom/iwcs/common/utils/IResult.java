package com.wisdom.iwcs.common.utils;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IResult<T> {
    private int returnCode = 200;
    private String returnMsg = "操作成功";
    private T returnData;

    public IResult(T returnData) {
        this.returnCode = HttpStatus.OK.value();
        this.returnMsg = "操作成功";
        this.returnData = returnData;
    }

    public IResult(int returnCode, String returnMsg) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.returnData = (T) ((returnData instanceof List) ? new ArrayList<>() : new HashMap<String, Object>());
    }

    public IResult(int returnCode, String returnMsg, T returnData) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.returnData = returnData;
    }

    public IResult() {
        super();
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public T getReturnData() {
        return returnData;
    }

    public void setReturnData(T returnData) {
        this.returnData = returnData;
    }

    private static <T> T createInstance(Class<T> cls) {
        T obj = null;
        try {
            obj = cls.newInstance();
        } catch (Exception ignored) {
        }
        return obj;
    }
}
