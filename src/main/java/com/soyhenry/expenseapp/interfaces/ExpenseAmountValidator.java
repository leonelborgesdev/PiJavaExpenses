package com.soyhenry.expenseapp.interfaces;


import com.soyhenry.expenseapp.exception.InvalidExpenseException;

public interface ExpenseAmountValidator {
    boolean notValidAmount(double amount) throws InvalidExpenseException;
}
