package com.soyhenry.expenseapp.dao;


import com.soyhenry.expenseapp.dao.dto.ExpenseDto;
import com.soyhenry.expenseapp.entities.Expense;
import com.soyhenry.expenseapp.exception.DAOException;

import java.util.List;

public interface ExpenseRepository {
    Integer insert(Expense expense);
    List<Expense> getAll();
    Integer updateExpense(Long id, Expense expense);
    void deleteExpense(Long id) throws DAOException;
    Expense selectExpenseById(Long id);
}
