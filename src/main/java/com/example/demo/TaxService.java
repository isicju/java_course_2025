package com.example.demo;

public class TaxService {

    public double getNetMonthlySalary(double gross) {
        final double PERSONAL_ALLOWANCE = 12570.0;
        final double BASIC_RATE_THRESHOLD = 50270.0;
        final double HIGHER_RATE_THRESHOLD = 125140.0;
        final double BASIC_RATE = 0.20;
        final double HIGHER_RATE = 0.40;
        final double ADDITIONAL_RATE = 0.45;

        final double NI_LOWER_THRESHOLD = 9112.0;
        final double NI_UPPER_THRESHOLD = 50270.0;
        final double NI_BASIC_RATE = 0.08;
        final double NI_HIGHER_RATE = 0.02;

        double personalAllowance = PERSONAL_ALLOWANCE;
        if (gross > 100000) {
            personalAllowance = Math.max(0, PERSONAL_ALLOWANCE - (gross - 100000) / 2.0);
        }

        double taxableIncome = Math.max(0, gross - personalAllowance);
        double incomeTax = 0.0;

        if (taxableIncome > 0) {
            if (gross <= BASIC_RATE_THRESHOLD) {
                incomeTax = taxableIncome * BASIC_RATE;
            } else if (gross <= HIGHER_RATE_THRESHOLD) {
                incomeTax = (BASIC_RATE_THRESHOLD - personalAllowance) * BASIC_RATE;
                incomeTax += (gross - BASIC_RATE_THRESHOLD) * HIGHER_RATE;
            } else {
                incomeTax = (BASIC_RATE_THRESHOLD - personalAllowance) * BASIC_RATE;
                incomeTax += (HIGHER_RATE_THRESHOLD - BASIC_RATE_THRESHOLD) * HIGHER_RATE;
                incomeTax += (gross - HIGHER_RATE_THRESHOLD) * ADDITIONAL_RATE;
            }
        }

        double niContributions = 0.0;
        if (gross > NI_LOWER_THRESHOLD) {
            if (gross <= NI_UPPER_THRESHOLD) {
                niContributions = (gross - NI_LOWER_THRESHOLD) * NI_BASIC_RATE;
            } else {
                niContributions = (NI_UPPER_THRESHOLD - NI_LOWER_THRESHOLD) * NI_BASIC_RATE;
                niContributions += (gross - NI_UPPER_THRESHOLD) * NI_HIGHER_RATE;
            }
        }

        double netAnnualSalary = gross - incomeTax - niContributions;
        return netAnnualSalary / 12.0;
    }

}
