package com.wisdom.iwcs.domain.hikSync;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/19 10:11
 */
public class SyncNotifyBinsDto {
    /**
     * 仓位编号
     */
    private String binCode;

    /**
     * 仓位类型
     */
    private String stgBinTyp;

    public String getBinCode() {
        return binCode;
    }

    public void setBinCode(String binCode) {
        this.binCode = binCode;
    }

    public String getStgBinTyp() {
        return stgBinTyp;
    }

    public void setStgBinTyp(String stgBinTyp) {
        this.stgBinTyp = stgBinTyp;
    }
}
