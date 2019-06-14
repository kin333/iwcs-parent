package com.wisdom.iwcs.common.utils;

/**
 * 删除标志枚举类
 * 删除标志：0为有效1无效
 * 删除(0,VALID)--》
 * 正常(1,NOT_VALID)--》
 *
 * @author cecilia.yang
 * @version <br>
 * <p>类的描述</p>
 */
public enum ValidFlagEnum {
    VALID(0, "有效"), NOT_VALID(1, "无效");
    private int status;
    private String message;

    ValidFlagEnum(int status, String message) {
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
