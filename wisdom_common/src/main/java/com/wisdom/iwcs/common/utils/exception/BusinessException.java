package com.wisdom.iwcs.common.utils.exception;

public class BusinessException extends RuntimeException {
    private String errorMsg;

    public BusinessException(ApplicationErrorEnum error) {
        this.errorMsg = error.getResMsg();
    }

    public BusinessException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
