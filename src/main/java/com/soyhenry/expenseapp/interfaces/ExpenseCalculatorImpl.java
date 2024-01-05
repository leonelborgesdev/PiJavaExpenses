package com.soyhenry.expenseapp.interfaces;

import com.soyhenry.expenseapp.dao.dto.ExpenseDto;
import com.soyhenry.expenseapp.entities.Expense;

import java.util.List;

public class ExpenseCalculatorImpl implements ExpenseCalculator{
    @Override
    public double calculateExpense(Expense expense) {
        return expense.getAmount();
    }

    @Override
    public double calculateTotalExpense(List<ExpenseDto> expenses) {
        double totalExpenses=0;
        for (ExpenseDto expense: expenses){
            totalExpenses += expense.getAmount();
        }
        return totalExpenses;
    }
}
