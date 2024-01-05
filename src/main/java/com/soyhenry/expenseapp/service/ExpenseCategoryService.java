package com.soyhenry.expenseapp.service;

import com.soyhenry.expenseapp.dto.request.ExpenseCategoryRequestDto;
import com.soyhenry.expenseapp.dto.response.ExpenseCategoryResponseDto;

import java.util.List;

public interface ExpenseCategoryService {
    String createCategory(ExpenseCategoryRequestDto expenseCategoryRequestDto);
    List<ExpenseCategoryResponseDto> getAllCategories();
    String updateCategory(Long id, ExpenseCategoryRequestDto categoryRequestDto);
}
