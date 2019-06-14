package com.wisdom.iwcs.domain.control;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/4 14:47
 */
public class InitPodRequestDTO {

    private String wbCode;

    private List<String> bincodes;

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    public List<String> getBincodes() {
        return bincodes;
    }

    public void setBincodes(List<String> bincodes) {
        this.bincodes = bincodes;
    }
}
