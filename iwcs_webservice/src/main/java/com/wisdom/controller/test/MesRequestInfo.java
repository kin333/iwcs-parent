package com.wisdom.controller.test;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MesRequestInfo {
    private String reqcode;
    private String taskCode;
    private String supplyLoadWb;
    private Integer supplyLoadNum;
    private String emptyRecyleWb;
    private Integer emptyRecyleNum;
    private String supplyUnLoadWb;
    private Integer supplyUnLoadNum;
    private String currentWb;
    private String agvCode;
    private String taskSta;

}
