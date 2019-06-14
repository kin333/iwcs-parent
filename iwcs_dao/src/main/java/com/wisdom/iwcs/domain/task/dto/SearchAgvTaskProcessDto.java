package com.wisdom.iwcs.domain.task.dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchAgvTaskProcessDto {
    private List<AgvTaskDetailOutstockProcessDTO> agvTaskDetailOutstockProcessDTOList;

}
