package com.wisdom.iwcs.domain.system;

import com.wisdom.iwcs.domain.codec.BusinessCode;

/**
 * @author Devin
 * @date 2018-03-23.
 */
public class BusinessCodeSearchDTO extends BusinessCode {

    private String searchKey;

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }
}
