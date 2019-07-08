package com.wisdom.iwcs.common.utils.exception;

public class TaskConditionException extends BusinessException {

    private int code;
    private String taskNum;
    private String condition;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }



    public TaskConditionException(int code, String msg) {
        this.code = code;
        super.setMsg(msg);
    }

    public TaskConditionException(int code, String msg, String taskNum, String condition) {
        this.code = code;
        super.setMsg(msg);
        this.taskNum = taskNum;
        this.condition = condition;
    }
}
