package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ReportModel;
@Repository
public class ReportrepositoryImpl implements ReportRepository {
	@Autowired
	JdbcTemplate template;

	@Override
	public List<ReportModel> getCategoryWiseReport(int uid, Date startDate, Date endDate) {
		String sql = "SELECT c.cname, SUM(e.eprice) AS totalExpense " +
                "FROM expense e JOIN category c ON e.cid = c.cid " +
                "WHERE e.expense_date BETWEEN ? AND ? AND e.uid = ? " +
                "GROUP BY c.cname";

   return template.query(sql, new Object[]{startDate, endDate, uid}, (rs, rowNum) -> {
       ReportModel report = new ReportModel();
       report.setCategoryName(rs.getString("cname"));
       report.setTotalExpense(rs.getFloat("totalExpense"));
       return report;
   });
	}

}
