package com.example.demo.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetModel {
	 private int bid;
	    private int uid;
	    private float budgetAmount;
	    private Date startDate;
	    private Date endDate;

}
