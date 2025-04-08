package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.model.ReportModel;

public interface ReportService {

	List<ReportModel> getCategoryWiseReport(int uid, Date startDate, Date endDate);

}
