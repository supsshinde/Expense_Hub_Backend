package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ReportModel;
import com.example.demo.service.ReportService;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/category")
    public List<ReportModel> getCategoryReport(
            @RequestParam int uid,
            @RequestParam String start,
            @RequestParam String end) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(start);
        Date endDate = sdf.parse(end);

        return reportService.getCategoryWiseReport(uid, startDate, endDate);
    }
}
