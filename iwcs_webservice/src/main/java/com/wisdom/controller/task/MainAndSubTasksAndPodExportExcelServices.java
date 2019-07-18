package com.wisdom.controller.task;

import com.github.crab2died.ExcelUtils;
import com.wisdom.iwcs.common.utils.ExportExcelsUtil;
import com.wisdom.iwcs.domain.base.dto.BasePodDetailDTO;
import com.wisdom.iwcs.domain.task.dto.MainTaskDTO;
import com.wisdom.iwcs.domain.task.dto.SubTaskDTO;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class MainAndSubTasksAndPodExportExcelServices {
    /**
     * 导出货架信息
     */
    public void exportDetail(List<BasePodDetailDTO> basePodDetailDTOList, HttpServletResponse response)throws Exception{
        response = ExportExcelsUtil.headerSet(response);
        ExcelUtils.getInstance().exportObjects2Excel(basePodDetailDTOList, BasePodDetailDTO.class, true, null, true,response.getOutputStream());
    }
    /**
     * 导出主任务信息
     */
    public void exportMainTask(List<MainTaskDTO> mainTaskDTOList, HttpServletResponse response)throws Exception{
        response=ExportExcelsUtil.headerSet(response);
        ExcelUtils.getInstance().exportObjects2Excel(mainTaskDTOList, MainTaskDTO.class, true, null, true,response.getOutputStream());
    }
    /**
     * 导出次任务信息
     */
    public void exportSubTask(List<SubTaskDTO> subTaskDTOList, HttpServletResponse response)throws Exception{
        response = ExportExcelsUtil.headerSet(response);
        ExcelUtils.getInstance().exportObjects2Excel(subTaskDTOList, SubTaskDTO.class, true, null, true,response.getOutputStream());
    }
}

