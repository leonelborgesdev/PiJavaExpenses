package com.soyhenry.expenseapp.dao;

import com.soyhenry.expenseapp.entities.ExpenseCategory;
import com.soyhenry.expenseapp.exception.DAOException;

import java.util.List;

public interface ExpenseCategoryRepository {
    Integer insert(ExpenseCategory expenseCategory);
    ExpenseCategory getCategoryByName(String name) throws DAOException;

    List<ExpenseCategory> getAllCategories();
    Integer updateCategory (Long id, ExpenseCategory expenseCategory);

}
