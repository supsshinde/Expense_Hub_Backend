package com.example.demo.model;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequest {
    private String ename;
    private int eprice;
    private String paymentMethod;
    private String description;
    private Date expenseDate;
    private int cid;

    private int uid;
}
