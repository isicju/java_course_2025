package com.example.demo;

public class TaxPair {
    private final double beforeIncome;
    private final double afterIncome;

    public TaxPair(double beforeIncome, double afterIncome) {
        this.beforeIncome = beforeIncome;
        this.afterIncome = afterIncome;
    }

    public double getBeforeTax() {
        return beforeIncome;
    }

    public double getAfterTax() {
        return afterIncome;
    }

}