package com.smartexpensetracker.expense;

public class ExpenseNotFoundException extends RuntimeException {
    public ExpenseNotFoundException(Long id) {
        super("Expense with id " + id + " was not found");
    }
}
