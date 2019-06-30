package com.wisdom.iwcs.service.task.conditions;

import com.wisdom.iwcs.service.task.subtask.intf.CondIntf;
import lombok.Data;

@Data
public abstract class ConditionBase implements CondIntf {
    private int order;


}
