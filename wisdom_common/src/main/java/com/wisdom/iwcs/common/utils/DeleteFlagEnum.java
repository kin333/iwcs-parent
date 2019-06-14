package com.wisdom.iwcs.common.utils;

/**
 * 删除标志枚举类
 * 删除标志：0为删除1为正常
 * 删除(0,IS_DELETE)--》
 * 正常(1,IS_OK)--》
 *
 * @author zhanglisen
 * @version <br>
 * <p>类的描述</p>
 */
public enum DeleteFlagEnum {
    DELETED(1, "删除"), NOT_DELETED(0, "正常");
    private int status;
    private String message;

    DeleteFlagEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }


    @Override
    public String toString() {
        return String.valueOf(this.status);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获得描述字符串--用于记录log
     *
     * @param
     * @return
     */
    public String getDisplayStr() {
        return "{ 'status': '" + this.status + "';'" + "'message':'" + this.getMessage() + "'}";
    }


}
