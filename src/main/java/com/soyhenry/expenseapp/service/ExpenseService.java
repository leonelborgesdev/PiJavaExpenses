package com.soyhenry.expenseapp.service;

import com.soyhenry.expenseapp.dto.request.ExpenseRequestDto;
import com.soyhenry.expenseapp.dto.response.ExpenseResponseDto;
import com.soyhenry.expenseapp.exception.DAOException;

import java.util.List;

public interface ExpenseService {
    String createExpense(ExpenseRequestDto expenseRequestDto) throws DAOException;
    List<ExpenseResponseDto> getAllExpenses();
    String updateExpense(Long id, ExpenseRequestDto expenseResponseDto);
    void deleteExpense(Long id) throws DAOException;
    ExpenseResponseDto getExpenseById(Long id);
}
