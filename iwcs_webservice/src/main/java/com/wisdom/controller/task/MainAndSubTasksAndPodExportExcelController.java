package com.wisdom.controller.task;

import com.wisdom.iwcs.domain.base.dto.BasePodDetailDTO;
import com.wisdom.iwcs.domain.task.dto.MainTaskDTO;
import com.wisdom.iwcs.domain.task.dto.SubTaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 * 对主任务、子任务、货架的操作，导出Excel表格
 *
 * @author baoxun
 * @version 19/07/15
 */
@RestController
@RequestMapping("/api/csptaskexportexcel")
public class MainAndSubTasksAndPodExportExcelController {
    @Autowired
    MainAndSubTasksAndPodExportExcelServices MainAndSubTasksAndPodExportExcelServices;
    /**
     * 导出货架信息
     *
     * @param "List<BasePodDetailDTO>"{@link List<BasePodDetailDTO>}
     * @author baoxun
     * @date 2019-07-15
     */
    @RequestMapping("/poddetailexport")
    public void podDetailExport(@RequestBody List<BasePodDetailDTO> basePodDetailDTOList, HttpServletResponse response)throws Exception{
        MainAndSubTasksAndPodExportExcelServices.exportDetail(basePodDetailDTOList,response);
    }
    /**
     * 导出主任务信息
     *
     * @param "List<MainTaskDTO>"{@link List<MainTaskDTO>}
     * @author baoxun
     * @date 2019-07-17
     */
    @RequestMapping("/maintaskexport")
    public void mainTaskExport(@RequestBody List<MainTaskDTO> mainTaskDTOList, HttpServletResponse response)throws Exception{
        MainAndSubTasksAndPodExportExcelServices.exportMainTask(mainTaskDTOList,response);
    }
    /**
     * 导出次任务信息
     *
     * @param "List<SubTaskDTO>"{@link List<SubTaskDTO>}
     * @author baoxun
     * @date 2019-07-17
     */
    @RequestMapping("/subtaskexport")
    public void subTaskExport(@RequestBody List<SubTaskDTO> subTaskDTOList, HttpServletResponse response)throws Exception{
        MainAndSubTasksAndPodExportExcelServices.exportSubTask(subTaskDTOList,response);
    }
}