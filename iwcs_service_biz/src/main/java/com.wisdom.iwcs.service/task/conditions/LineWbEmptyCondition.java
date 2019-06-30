package com.wisdom.iwcs.service.task.conditions;

import com.wisdom.iwcs.service.task.subtask.intf.CondIntf;

public class LineWbEmptyCondition implements CondIntf {
    @Override
    public boolean canMeet() {
        return false;
    }

}
