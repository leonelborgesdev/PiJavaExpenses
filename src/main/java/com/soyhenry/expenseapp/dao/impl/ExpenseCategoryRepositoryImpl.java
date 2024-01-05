package com.soyhenry.expenseapp.dao.impl;


import com.soyhenry.expenseapp.dao.ExpenseCategoryRepository;
import com.soyhenry.expenseapp.dao.dto.ExpenseCategoryDto;
import com.soyhenry.expenseapp.entities.ExpenseCategory;
import com.soyhenry.expenseapp.exception.DAOException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ExpenseCategoryRepositoryImpl implements ExpenseCategoryRepository {
    private static final String GET_ALL_CATEGORIES="SELECT * FROM expensecategory";
    private static final String GET_CATEGORY_BY_NAME="SELECT * FROM expensecategory WHERE name = ?";
    private static final String INSERT_INTO_EXPENSE_CATEGORY="INSERT INTO expensecategory (name) VALUES (?)";
    private static final String UPDATE_CATEGORY_BY_ID="UPDATE expensecategory SET name = ? WHERE id = ?";
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
        return jdbcTemplate.query(GET_ALL_CATEGORIES, new CategoryRowMapper());
    }

    @Override
    public Integer updateCategory(Long id, ExpenseCategory expenseCategory) {
        return jdbcTemplate.update(UPDATE_CATEGORY_BY_ID,
                expenseCategory.getName(),id);
    }

    static class CategoryRowMapper implements RowMapper<ExpenseCategory>{

        @Override
        public ExpenseCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExpenseCategory category=new ExpenseCategory();

            category.setId(rs.getLong("id"));
            category.setName(rs.getString("name"));
            return category;
        }
    }
}
