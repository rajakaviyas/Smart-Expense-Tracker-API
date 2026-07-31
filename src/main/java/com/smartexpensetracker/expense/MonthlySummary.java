package com.smartexpensetracker.expense;

import java.math.BigDecimal;
import java.util.Map;

public class MonthlySummary {
    private final int year;
    private final int month;
    private final int expenseCount;
    private final BigDecimal total;
    private final Map<String, BigDecimal> totalsByCategory;

    public MonthlySummary(int year, int month, int expenseCount, BigDecimal total,
                          Map<String, BigDecimal> totalsByCategory) {
        this.year = year;
        this.month = month;
        this.expenseCount = expenseCount;
        this.total = total;
        this.totalsByCategory = totalsByCategory;
    }

    public int getYear() { return year; }
    public int getMonth() { return month; }
    public int getExpenseCount() { return expenseCount; }
    public BigDecimal getTotal() { return total; }
    public Map<String, BigDecimal> getTotalsByCategory() { return totalsByCategory; }
}
