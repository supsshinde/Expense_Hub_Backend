package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ReportModel;
import com.example.demo.repository.ReportrepositoryImpl;
@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	ReportrepositoryImpl reportRepo;

	

	@Override
	public List<ReportModel> getCategoryWiseReport(int uid, java.util.Date startDate, java.util.Date endDate) {
		// TODO Auto-generated method stub
		return reportRepo.getCategoryWiseReport(uid, null, null);
	}

}
