package com.wisdom.iwcs.domain.task.dto;

import com.wisdom.iwcs.domain.task.MainTask;

import java.util.List;

public class MainTaskWithSubTaskInfos extends MainTask {
    private List<SubTaskInfo> subTaskInfos;

    public List<SubTaskInfo> getSubTaskInfos() {
        return subTaskInfos;
    }

    public void setSubTaskInfos(List<SubTaskInfo> subTaskInfos) {
        this.subTaskInfos = subTaskInfos;
    }
}
