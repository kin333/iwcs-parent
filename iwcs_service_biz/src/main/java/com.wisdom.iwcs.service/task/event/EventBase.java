package com.wisdom.iwcs.service.task.event;

import lombok.Data;

@Data
public class EventBase {
    public EventBase(String eventType, String taskId, String subTaskId){
        this.eventType = eventType;
        this.taskId = taskId;
        this.subTaskId = subTaskId;
    }
    private String eventType;
    private String taskId;
    private String subTaskId;
}
