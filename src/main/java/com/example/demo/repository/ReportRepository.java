package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import com.example.demo.model.ReportModel;

public interface ReportRepository {
	public List<ReportModel> getCategoryWiseReport(int uid, Date startDate, Date endDate);

}
