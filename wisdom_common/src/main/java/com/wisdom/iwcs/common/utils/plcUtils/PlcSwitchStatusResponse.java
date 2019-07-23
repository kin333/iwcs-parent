package com.wisdom.iwcs.common.utils.plcUtils;

import java.util.List;

/**
 * PLC 返回值
 * @Author george
 * @Date 2019/7/16 15:25
 */
public class PlcSwitchStatusResponse extends PlcRespone {

    List<PLCSwitch> listPLCSwitchList;

    public List<PLCSwitch> getListPLCSwitchList() {
        return listPLCSwitchList;
    }

    public void setListPLCSwitchList(List<PLCSwitch> listPLCSwitchList) {
        this.listPLCSwitchList = listPLCSwitchList;
    }
}
