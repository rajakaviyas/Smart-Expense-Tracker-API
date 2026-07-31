package com.smartexpensetracker.expense;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExpenseServiceTest {

    @Test
    void calculatesOverallAndCategoryTotals() {
        ExpenseService service = new ExpenseService();
        service.add(new Expense(null, "Groceries", new BigDecimal("42.50"), "Food", LocalDate.of(2026, 7, 1)));
        service.add(new Expense(null, "Bus", new BigDecimal("10.00"), "Travel", LocalDate.of(2026, 7, 2)));
        service.add(new Expense(null, "Lunch", new BigDecimal("7.50"), "Food", LocalDate.of(2026, 7, 3)));

        assertEquals(new BigDecimal("60.00"), service.total(null));
        assertEquals(new BigDecimal("50.00"), service.total("food"));
        assertEquals(2, service.findAll("Food", null).size());
        assertEquals(1, service.findAll(null, "lunch").size());
    }

    @Test
    void summarizesExpensesForOneMonth() {
        ExpenseService service = new ExpenseService();
        service.add(new Expense(null, "Groceries", new BigDecimal("42.50"), "Food", LocalDate.of(2026, 7, 1)));
        service.add(new Expense(null, "Bus", new BigDecimal("10.00"), "Travel", LocalDate.of(2026, 7, 2)));
        service.add(new Expense(null, "August bill", new BigDecimal("30.00"), "Bills", LocalDate.of(2026, 8, 1)));

        MonthlySummary summary = service.monthlySummary(2026, 7);

        assertEquals(2, summary.getExpenseCount());
        assertEquals(new BigDecimal("52.50"), summary.getTotal());
        assertEquals(new BigDecimal("42.50"), summary.getTotalsByCategory().get("Food"));
    }
}
