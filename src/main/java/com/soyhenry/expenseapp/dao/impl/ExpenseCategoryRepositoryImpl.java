package com.soyhenry.expenseapp.dao.impl;


import com.soyhenry.expenseapp.dao.ExpenseCategoryRepository;
import com.soyhenry.expenseapp.dao.dto.ExpenseCategoryDto;
import com.soyhenry.expenseapp.entities.ExpenseCategory;
import com.soyhenry.expenseapp.exception.DAOException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenseCategoryRepositoryImpl implements ExpenseCategoryRepository {
    private static final String GET_CATEGORY_BY_NAME="SELECT * FROM expensecategory WHERE name = ?";
    private static final String INSERT_INTO_EXPENSE_CATEGORY="INSERT INTO expensecategory (name) VALUES (?)";
    private final JdbcTemplate jdbcTemplate;

    public ExpenseCategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer insert(ExpenseCategory expenseCategory) {
        return jdbcTemplate.update(INSERT_INTO_EXPENSE_CATEGORY, expenseCategory.getName().toLowerCase());
    }

    @Override
    public ExpenseCategory getCategoryByName(String name) throws DAOException {
        return null;
    }

    @Override
    public List<ExpenseCategory> getAllCategories() {
        return null;
    }

    @Override
    public Integer updateCategory(Long id, ExpenseCategory expenseCategory) {
        return null;
    }

    private ExpenseCategory mapDtoToExpenseCategory(ExpenseCategoryDto expenseCategoryDto){
        ExpenseCategory expenseCategory= new ExpenseCategory();
        expenseCategory.setName(expenseCategoryDto.getName());
        return expenseCategory;
    }
}
