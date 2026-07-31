package com.smartexpensetracker.expense;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final Map<Long, Expense> expenses = new ConcurrentHashMap<Long, Expense>();
    private final AtomicLong nextId = new AtomicLong(1);

    public Expense add(Expense expense) {
        long id = nextId.getAndIncrement();
        Expense saved = new Expense(id, expense.getTitle().trim(), expense.getAmount(),
                expense.getCategory().trim(), expense.getDate());
        expenses.put(id, saved);
        return saved;
    }

    public List<Expense> findAll(String category, String search) {
        return expenses.values().stream()
                .filter(expense -> category == null || category.trim().isEmpty()
                        || expense.getCategory().equalsIgnoreCase(category.trim()))
                .filter(expense -> search == null || search.trim().isEmpty()
                        || expense.getTitle().toLowerCase(Locale.ROOT).contains(search.trim().toLowerCase(Locale.ROOT)))
                .sorted(Comparator.comparing(Expense::getDate).reversed()
                        .thenComparing(Expense::getId, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public BigDecimal total(String category) {
        return findAll(category, null).stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<String, BigDecimal> totalsByCategory() {
        return expenses.values().stream().collect(Collectors.groupingBy(
                Expense::getCategory,
                Collectors.reducing(BigDecimal.ZERO, Expense::getAmount, BigDecimal::add)
        ));
    }

    public MonthlySummary monthlySummary(int year, int month) {
        YearMonth requestedMonth = YearMonth.of(year, month);
        List<Expense> monthlyExpenses = expenses.values().stream()
                .filter(expense -> YearMonth.from(expense.getDate()).equals(requestedMonth))
                .collect(Collectors.toList());
        Map<String, BigDecimal> categoryTotals = monthlyExpenses.stream().collect(Collectors.groupingBy(
                Expense::getCategory,
                LinkedHashMap::new,
                Collectors.reducing(BigDecimal.ZERO, Expense::getAmount, BigDecimal::add)
        ));
        BigDecimal total = monthlyExpenses.stream().map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new MonthlySummary(year, month, monthlyExpenses.size(), total, categoryTotals);
    }

    public void delete(Long id) {
        if (expenses.remove(id) == null) {
            throw new ExpenseNotFoundException(id);
        }
    }
}
