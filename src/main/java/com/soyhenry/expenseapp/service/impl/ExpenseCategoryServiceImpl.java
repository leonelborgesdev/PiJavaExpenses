package com.soyhenry.expenseapp.service.impl;

import com.soyhenry.expenseapp.dao.ExpenseCategoryRepository;
import com.soyhenry.expenseapp.dto.request.ExpenseCategoryRequestDto;
import com.soyhenry.expenseapp.dto.response.ExpenseCategoryResponseDto;
import com.soyhenry.expenseapp.entities.ExpenseCategory;
import com.soyhenry.expenseapp.service.ExpenseCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {
    private final ExpenseCategoryRepository expenseCategoryRepository;

    public ExpenseCategoryServiceImpl(ExpenseCategoryRepository expenseCategoryRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
    }

    @Override
    public String createCategory(ExpenseCategoryRequestDto expenseCategoryRequestDto) {
        return null;
    }

    @Override
    public List<ExpenseCategoryResponseDto> getAllCategories() {
        List<ExpenseCategory> categories= expenseCategoryRepository.getAllCategories();
        return categories.stream()
                .map(this::mapCategoryToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public String updateCategory(Long id, ExpenseCategoryRequestDto categoryRequestDto) {
        return null;
    }

    private ExpenseCategoryResponseDto mapCategoryToResponseDto(ExpenseCategory expenseCategory){
        ExpenseCategoryResponseDto expenseCategoryResponseDto=new ExpenseCategoryResponseDto();

        expenseCategoryResponseDto.setId(expenseCategory.getId());
        expenseCategoryResponseDto.setName(expenseCategory.getName());

        return expenseCategoryResponseDto;
    }
}
