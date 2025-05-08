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
import com.example.demo.service.ExpenseService;
import com.example.demo.service.ReportService;
 
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private ExpenseService expenseService;
    
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
    
//    // Save all transaction in CSV or PDF format
//    @GetMapping("/export/csv/{uid}")
//    public void exportToCSV(@PathVariable int uid, HttpServletResponse response) throws IOException {
//        response.setContentType("text/csv");
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=transactions_" + uid + ".csv";
//        response.setHeader(headerKey, headerValue);
//
//        List<ExpenseModel> expenses = expenseService.getExpensesByUid(uid);
//        PrintWriter writer = response.getWriter();
//
//        // Write CSV Header
//        writer.println("ID,Category,Amount,Date,Note");
//
//        for (ExpenseModel exp : expenses) {
//            writer.println(exp.getEid() + "," +exp.getEname()+ "," +exp.getEprice()+ ","  +exp.getPaymentMethod()+ "," +exp.getExpenseDate()+
//                    exp.getCid() + "," + exp.getUid()+ "," +exp.getCategoryName());
//        }
//
//        writer.flush();
//        writer.close();
//    }
}
