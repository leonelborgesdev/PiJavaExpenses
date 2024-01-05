package com.soyhenry.expenseapp.interfaces;


import com.soyhenry.expenseapp.exception.InvalidExpenseException;

public class ExpenseAmountValidatorImpl implements ExpenseAmountValidator{
    @Override
    public boolean notValidAmount(double amount) throws InvalidExpenseException {
        if (amount < 0){
            throw  new InvalidExpenseException("El monto debe ser igual o mayor a cero");
        }
        return false;
    }
}
