package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseReportResponse {
    private List<ExpenseModel> expenseList;
    private float totalExpenseAmount;
}
