package io.spring.Controller;

import io.spring.Entity.Report;
import io.spring.Service.ReportService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/reports")
public class ReportController {

    List<Report> reportList = new ArrayList<>();
    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/list")
    public ModelAndView listReport(){
        ModelAndView mav = new ModelAndView("/main/admin/report/list");

        reportList = reportService.fetchAll();
        mav.addObject("reportList",reportList);

        return mav;
    }
}
