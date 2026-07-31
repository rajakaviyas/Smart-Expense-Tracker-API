package com.smartexpensetracker.expense;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Expense addExpense(@Valid @RequestBody Expense expense) {
        return expenseService.add(expense);
    }

    @GetMapping
    public List<Expense> getExpenses(@RequestParam(required = false) String category,
                                     @RequestParam(required = false) String search) {
        return expenseService.findAll(category, search);
    }

    @GetMapping("/total")
    public Map<String, BigDecimal> getTotal(@RequestParam(required = false) String category) {
        return java.util.Collections.singletonMap("total", expenseService.total(category));
    }

    @GetMapping("/totals-by-category")
    public Map<String, BigDecimal> getTotalsByCategory() {
        return expenseService.totalsByCategory();
    }

    @GetMapping("/summary/monthly")
    public MonthlySummary getMonthlySummary(@RequestParam @Min(1) int year,
                                            @RequestParam @Min(1) @Max(12) int month) {
        return expenseService.monthlySummary(year, month);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExpense(@PathVariable Long id) {
        expenseService.delete(id);
    }
}
