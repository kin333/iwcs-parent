package com.wisdom.iwcs.domain.hikSync;

/**
 * 结束工作台任务
 *
 * @Author cecilia.yang
 */
public class EndTaskByTpsDTO {

    /**
     * 请求编号，每个请求都要一个唯一编号， 同一个请求重复提交， 使用同一编号
     */
    private String reqCode;

    /**
     * 请求时间截 格式: “yyyy-MM-dd HH:mm:ss”
     */
    private String reqTime;

    /**
     * 客户端编号，如PDA，HCWMS等
     */
    private String clientCode;

    /**
     * 令牌号, 由调度系统颁发
     */
    private String tokenCode;

    /**
     * 工作台编号
     */
    private String wbCode;

    @Override
    public String toString() {
        return "EndTaskByTpsDTO{" +
                "reqCode='" + reqCode + '\'' +
                ", reqTime='" + reqTime + '\'' +
                ", clientCode='" + clientCode + '\'' +
                ", tokenCode='" + tokenCode + '\'' +
                ", wbCode='" + wbCode + '\'' +
                '}';
    }

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }
}
