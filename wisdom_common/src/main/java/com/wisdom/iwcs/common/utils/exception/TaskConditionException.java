package com.wisdom.iwcs.common.utils.exception;

public class TaskConditionException extends RuntimeException {

    private int code;
    private String msg;
    private String taskNum;
    private String condition;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TaskConditionException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public TaskConditionException(int code, String msg, String taskNum, String condition) {
        this.code = code;
        this.msg = msg;
        this.taskNum = taskNum;
        this.condition = condition;
    }
}
