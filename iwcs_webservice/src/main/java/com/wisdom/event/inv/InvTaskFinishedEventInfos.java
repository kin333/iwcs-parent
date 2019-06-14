package com.wisdom.event.inv;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 盘点确认结束事件参数
 *
 * @Auther: panzun
 * @Date: 2019-3-21 09:29
 * @Description:
 */
@Getter
@Setter
@ToString
public class InvTaskFinishedEventInfos {
    private String srcInvNo;
    private String wbCode;

    public String getSrcInvNo() {
        return srcInvNo;
    }

    public void setSrcInvNo(String srcInvNo) {
        this.srcInvNo = srcInvNo;
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }
}
