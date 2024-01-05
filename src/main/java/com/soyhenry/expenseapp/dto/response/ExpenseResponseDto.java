package com.soyhenry.expenseapp.dto.response;


public class ExpenseResponseDto {
    private Long id;
    private Double amount;
    private ExpenseCategoryResponseDto categoryRequestDto;
    private String date;

    public ExpenseResponseDto( ){

    }

    public ExpenseResponseDto(Long id, Double amount, ExpenseCategoryResponseDto categoryRequestDto, String date) {
        this.id = id;
        this.amount = amount;
        this.categoryRequestDto = categoryRequestDto;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public ExpenseCategoryResponseDto getCategoryRequestDto() {
        return categoryRequestDto;
    }

    public void setCategoryRequestDto(ExpenseCategoryResponseDto categoryRequestDto) {
        this.categoryRequestDto = categoryRequestDto;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
