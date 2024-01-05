package com.soyhenry.expenseapp.service.impl;

import com.soyhenry.expenseapp.dto.request.ExpenseCategoryRequestDto;
import com.soyhenry.expenseapp.dto.response.ExpenseCategoryResponseDto;
import com.soyhenry.expenseapp.service.ExpenseCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {
    @Override
    public String createCategory(ExpenseCategoryRequestDto expenseCategoryRequestDto) {
        return null;
    }

    @Override
    public List<ExpenseCategoryResponseDto> getAllCategories() {
        return null;
    }

    @Override
    public String updateCategory(Long id, ExpenseCategoryRequestDto categoryRequestDto) {
        return null;
    }
}
