package com.soyhenry.expenseapp.dao.impl;


import com.soyhenry.expenseapp.dao.ExpenseRepository;
import com.soyhenry.expenseapp.entities.Expense;
import com.soyhenry.expenseapp.entities.ExpenseCategory;
import com.soyhenry.expenseapp.exception.DAOException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ExpenseRepositoryImpl implements ExpenseRepository {
    private static final String SELECT_EXPENSE_BY_ID= "SELECT * FROM Expense WHERE id= ?";
    private static final String GET_ALL_EXPENSES="SELECT * FROM Expense";
    private static final String INSERT_INTO_EXPENSE="INSERT INTO Expense(amount, category_id, category_name, date) VALUES (?,?,?,?)";
    private static final String UPDATE_EXPENSE_BY_ID = "UPDATE Expense SET amount = ?, category_id=?, date= ? WHERE id= ?";
    private static final String DELETE_EXPENSE_BY_ID= "DELETE FROM Expense WHERE id = ?";
    private static final String INSERT_INTO_CATEGORY_EXPENSE = "INSERT INTO ExpenseCategory (name) VALUES (?)";
    private static final String SELECT_FROM_EXPENSE_CATEGORY_BY_NAME="SELECT * FROM ExpenseCategory WHERE name = ?";
    private final JdbcTemplate jdbcTemplate;

    public ExpenseRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer insert(Expense expense) {
        jdbcTemplate.update(INSERT_INTO_CATEGORY_EXPENSE,expense.getCategoryName().toLowerCase());
        Object[] params = {expense.getCategoryName()};
        int[] types={1};
        ExpenseCategory expenseCategory= jdbcTemplate.queryForObject(
                SELECT_FROM_EXPENSE_CATEGORY_BY_NAME,
                params,types, new ExpenseCategoryRowMapper());
        return jdbcTemplate.update(INSERT_INTO_EXPENSE,
                expense.getAmount(),
                expenseCategory.getId(),
                expenseCategory.getName(),
                expense.getDate());
    }
    static class ExpenseCategoryRowMapper implements RowMapper<ExpenseCategory>{
            public ExpenseCategory mapRow(ResultSet rs, int rowNum) throws SQLException{
                ExpenseCategory expenseCategory=new ExpenseCategory();
                expenseCategory.setId(rs.getLong("id"));
                expenseCategory.setName(rs.getString("name"));
                return expenseCategory;
            }
    }

    @Override
    public List<Expense> getAll() {
        return  jdbcTemplate.query(GET_ALL_EXPENSES,new ExpenseRowMapper());
    }

    @Override
    public Integer updateExpense(Long id, Expense expense) {
        return jdbcTemplate.update(UPDATE_EXPENSE_BY_ID,
                expense.getAmount(),
                expense.getCategoryId(),
                expense.getDate(),
                id);
    }

    static class ExpenseRowMapper implements RowMapper<Expense>{

        @Override
        public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
            Expense expense=new Expense();
            expense.setId(rs.getLong("id"));
            expense.setAmount(rs.getDouble("amount"));
            expense.setCategoryId(rs.getLong("category_id"));
            expense.setCategoryName(rs.getString("category_name"));
            expense.setDate(rs.getString("date"));
            return expense;
        }
    }


    @Override
    public void deleteExpense(Long id) throws DAOException{
        System.out.println("Se elimina el gasto con Id: "+id);
        try{
            jdbcTemplate.update(DELETE_EXPENSE_BY_ID, id);
        }catch (DataAccessException exception){
            throw new DAOException("Hubo un error al eliminar el gasto con id" + id, exception);
        }
        System.out.println("Gasto eliminado con exito");
    }

    @Override
    public Expense selectExpenseById(Long id) {
        Object[] params= {id};
        int [] types= {1};
        return  jdbcTemplate.queryForObject(
                SELECT_EXPENSE_BY_ID,
                params, types,
                new ExpenseRowMapper());
    }
}
