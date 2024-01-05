package com.soyhenry.expenseapp.dao;

import com.soyhenry.expenseapp.dao.dto.ExpenseCategoryDto;
import com.soyhenry.expenseapp.entities.ExpenseCategory;
import com.soyhenry.expenseapp.exception.DAOException;

public interface ExpenseCategoryDao {
    void insert(ExpenseCategoryDto expense);
    ExpenseCategory getCategoryByName(String name) throws DAOException;
}
