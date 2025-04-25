package com.example.demo.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseModel {
    private int eid;
    private String ename;
    private float eprice;
    private String paymentMethod;
    private String description;
    private Date expenseDate;
    private int cid;
    private String categoryName;  // Add this field
    private int uid;
}


