package com.wisdom.iwcs.domain.mes;

/**
 * Agv到达等待点
 * @Author george
 * @Date 2019/8/27 11:10
 */
public class ArriveDestWbWaitPortInfoDTO {
    /**
     * 请求编号，每个请求都要一个唯一编号， 同一个请求重复提交， 使用同一编号
     */
    private String reqCode;

    /**
     * 消息体
     */
    private ArriveDestWbWaitPortRequest data;

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public ArriveDestWbWaitPortRequest getData() {
        return data;
    }

    public void setData(ArriveDestWbWaitPortRequest data) {
        this.data = data;
    }
}
