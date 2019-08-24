package com.wisdom.iwcs.common.utils.exception;

/**
 * 返回给Mes系统的业务异常
 * @author han
 */
public class MesBusinessException extends RuntimeException {
    private String msg;
    private String reqCode;
    private String taskCode;

    public MesBusinessException(ApplicationErrorEnum error) {
        this.msg = error.getResMsg();
    }

    public MesBusinessException(String errorMsg) {
        super(errorMsg);
        this.msg = errorMsg;
    }
    public MesBusinessException(String taskCode, String errorMsg) {
        super(errorMsg);
        this.msg = errorMsg;
        this.taskCode = taskCode;
    }

    @Override
    public String getMessage() {
        return msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public MesBusinessException() {
        super();
    }
}

