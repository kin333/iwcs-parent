package com.wisdom.iwcs.common.utils;

import java.io.Serializable;

public class FliterSelectData implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8821211722216087840L;
    private String option;
    private String value;

    public String getOption() {

        return option;
    }

    public void setOption(String option) {

        this.option = option;
    }

    public String getValue() {

        return value;
    }

    public void setValue(String value) {

        this.value = value;
    }


}
