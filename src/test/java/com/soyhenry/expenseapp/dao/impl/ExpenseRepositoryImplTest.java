package com.soyhenry.expenseapp.dao.impl;

import com.soyhenry.expenseapp.entities.Expense;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseRepositoryImplTest {
    @Test
    void  insert_ShouldInsertExpense(){
        //GIVEN
        Expense expense= new Expense();
        expense.setAmount(1000.00);
        expense.setDate("11-12-2023");
        expense.setCategoryName("Tecnologia");

    }

}