package com.soyhenry.expenseapp.dao.impl;


import com.soyhenry.expenseapp.dao.ExpenseCategoryDao;
import com.soyhenry.expenseapp.dao.dto.ExpenseCategoryDto;
import com.soyhenry.expenseapp.entities.ExpenseCategory;
import com.soyhenry.expenseapp.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseCategoryDaoImpl implements ExpenseCategoryDao {
    private static final String GET_CATEGORY_BY_NAME="SELECT * FROM expensecategory WHERE name = ?";
    private static final String INSERT_INTO_EXPENSE_CATEGORY="INSERT INTO expensecategory (name) VALUES (?)";
    private final Connection connection;

    public ExpenseCategoryDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(ExpenseCategoryDto expenseCategoryDto) {
        try (PreparedStatement statement= connection.prepareStatement(INSERT_INTO_EXPENSE_CATEGORY)){
            ExpenseCategory expenseCategory= mapDtoToExpenseCategory(expenseCategoryDto);

            statement.setString(1, expenseCategory.getName());
            int affectedRows= statement.executeUpdate();
            if (affectedRows == 0){
                throw  new DAOException("Error al insertar el gasto, ninguna fila fue afectada.");
            }

        } catch (SQLException | DAOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ExpenseCategory getCategoryByName(String name) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_CATEGORY_BY_NAME)){
            statement.setString(1,name);
            ResultSet resultSet= statement.executeQuery();
            if (resultSet.next()){
                return  new ExpenseCategory(resultSet.getLong("id"),resultSet.getString("name"));
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException("Error al obtener el gasto por Id", e);
        }
    }
    private ExpenseCategory mapDtoToExpenseCategory(ExpenseCategoryDto expenseCategoryDto){
        ExpenseCategory expenseCategory= new ExpenseCategory();
        expenseCategory.setName(expenseCategoryDto.getName());
        return expenseCategory;
    }
}
