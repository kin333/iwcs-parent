package com.wisdom.iwcs.domain.control;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/4 11:46
 */
public class CallOutByBincodeRequestDTO {
    /**
     * 呼叫用户
     */
    private String userId;
    /**
     * 呼叫编号
     */
    private String srcReqCode;
    /**
     * 点位
     */
    private String wbCode;
    /**
     * 库区编码
     */
    private String areaCode;
    /**
     * 呼叫货架
     */
    private List<String> callBincodes;

    @Override
    public String toString() {
        return "CallOutByBincodeRequestDTO{" +
                "userId='" + userId + '\'' +
                ", srcReqCode='" + srcReqCode + '\'' +
                ", wbCode='" + wbCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", callBincodes=" + callBincodes +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSrcReqCode() {
        return srcReqCode;
    }

    public void setSrcReqCode(String srcReqCode) {
        this.srcReqCode = srcReqCode;
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public List<String> getCallBincodes() {
        return callBincodes;
    }

    public void setCallBincodes(List<String> callBincodes) {
        this.callBincodes = callBincodes;
    }
}
