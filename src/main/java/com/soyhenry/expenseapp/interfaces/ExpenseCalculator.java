package com.soyhenry.expenseapp.interfaces;


import com.soyhenry.expenseapp.dao.dto.ExpenseDto;
import com.soyhenry.expenseapp.entities.Expense;

import java.util.List;

public interface ExpenseCalculator {
    double calculateExpense(Expense expense);
    double calculateTotalExpense(List<ExpenseDto> expenses);
}
