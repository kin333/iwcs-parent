package com.wisdom.controller.report;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    @PostMapping(value = "/post")
    public String getReportData() {
        return "[{\"CityId\":\"18\",\"CityName\":\"西安\"},{\"CityId\":\"53\",\"CityName\":\"广州\"}]";
    }
}
