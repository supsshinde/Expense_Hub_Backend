package com.example.demo.model;

public class ExpenseCategoryDistribution {
    private String category;
    private double amount;

    public ExpenseCategoryDistribution(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }
}

