package com.soyhenry.expenseapp.service.impl;

import com.soyhenry.expenseapp.dao.ExpenseRepository;
import com.soyhenry.expenseapp.dto.request.ExpenseRequestDto;
import com.soyhenry.expenseapp.dto.response.ExpenseCategoryResponseDto;
import com.soyhenry.expenseapp.dto.response.ExpenseResponseDto;
import com.soyhenry.expenseapp.entities.Expense;
import com.soyhenry.expenseapp.exception.DAOException;
import com.soyhenry.expenseapp.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public String createExpense(ExpenseRequestDto expenseRequestDto) throws DAOException {
        String response= "Se registro el gasto con exito";
        Expense expense= mapDtoToExpense(expenseRequestDto);
        Integer responseInserted= expenseRepository.insert(expense);
        if(responseInserted.equals(0)){
            return "No se inserto ningun registro";
        }
        return response;
    }
    private Expense mapDtoToExpense(ExpenseRequestDto expenseRequestDto){
        Expense expense=new Expense();
        expense.setAmount(expenseRequestDto.getAmount());
        expense.setCategoryId(expenseRequestDto.getCategoryRequestDto().getId());
        expense.setCategoryName(expenseRequestDto.getCategoryRequestDto().getName());
        expense.setDate(expenseRequestDto.getDate());
        return expense;
    }

    @Override
    public List<ExpenseResponseDto> getAllExpenses() {
        List<Expense> expenses = expenseRepository.getAll();
        return expenses.stream()
                .map(this::mapExpenseToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public String updateExpense(Long id, ExpenseRequestDto expenseResponseDto) {
        String response = "Se actualizo el gasto con exito";
        Expense expense=mapDtoToExpense(expenseResponseDto);
        Integer responseUpdate= expenseRepository.updateExpense(id, expense);
        if (responseUpdate.equals(0)){
            return "No se actualizo ningun registro con el id="+ id;
        }
        return response;
    }

    private ExpenseResponseDto mapExpenseToResponseDto(Expense expense){
        ExpenseResponseDto expenseResponseDto= new ExpenseResponseDto();
        expenseResponseDto.setAmount(expense.getAmount());

        ExpenseCategoryResponseDto categoryDto= new ExpenseCategoryResponseDto();
        categoryDto.setId(expense.getCategoryId());
        categoryDto.setName(expense.getCategoryName());

        expenseResponseDto.setId(expense.getId());
        expenseResponseDto.setCategoryRequestDto(categoryDto);
        expenseResponseDto.setDate(expense.getDate());
        return expenseResponseDto;
    }
}
