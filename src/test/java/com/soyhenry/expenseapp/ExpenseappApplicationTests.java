package com.soyhenry.expenseapp;



import com.soyhenry.expenseapp.dto.request.ExpenseCategoryRequestDto;
import com.soyhenry.expenseapp.dto.response.ExpenseCategoryResponseDto;
import com.soyhenry.expenseapp.service.ExpenseCategoryService;

import com.soyhenry.expenseapp.controller.ExpenseController;
import com.soyhenry.expenseapp.dto.request.ExpenseRequestDto;
import com.soyhenry.expenseapp.dto.response.ExpenseResponseDto;
import com.soyhenry.expenseapp.exception.DAOException;
import com.soyhenry.expenseapp.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ExpenseControllerTest {

	@Mock
	private ExpenseService expenseService;
	@Mock
	private ExpenseCategoryService expenseCategoryService;

	@InjectMocks
	private ExpenseController expenseController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateExpenseHandler() throws DAOException {
		ExpenseRequestDto requestDto = new ExpenseRequestDto();
		when(expenseService.createExpense(requestDto)).thenReturn("Expense created successfully");

		ResponseEntity<String> response = expenseController.createExpenseHandler(requestDto);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Expense created successfully", response.getBody());
		verify(expenseService, times(1)).createExpense(requestDto);
	}

	@Test
	public void testGetExpenses() {
		List<ExpenseResponseDto> expectedResponse = Arrays.asList(new ExpenseResponseDto(), new ExpenseResponseDto());
		when(expenseService.getAllExpenses()).thenReturn(expectedResponse);

		ResponseEntity<List<ExpenseResponseDto>> response = expenseController.getExpenses();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedResponse, response.getBody());
		verify(expenseService, times(1)).getAllExpenses();
	}

	@Test
	public void testUpdateExpense() {
		Long id = 1L;
		ExpenseRequestDto requestDto = new ExpenseRequestDto();
		when(expenseService.updateExpense(id, requestDto)).thenReturn("Expense updated successfully");

		ResponseEntity<String> response = expenseController.updateExpense(id, requestDto);

		assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		assertEquals("Expense updated successfully", response.getBody());
		verify(expenseService, times(1)).updateExpense(id, requestDto);
	}


	@Test
	void deleteExpense_shouldReturnGoneResponse() throws DAOException {
		// Arrange
		Long expenseId = 1L;

		// Act
		ResponseEntity<String> responseEntity = expenseController.deleteExpense(expenseId);

		// Assert
		assertEquals(HttpStatus.GONE, responseEntity.getStatusCode());
		assertEquals("Se elimino el gasto con id:" + expenseId, responseEntity.getBody());

		// Verify that the service method was called
		verify(expenseService, times(1)).deleteExpense(expenseId);
	}

	@Test
	void getExpenseById_shouldReturnExpenseResponse() {
		// Arrange
		Long expenseId = 1L;
		ExpenseResponseDto expenseResponseDto = new ExpenseResponseDto();
		when(expenseService.getExpenseById(expenseId)).thenReturn(expenseResponseDto);

		// Act
		ResponseEntity<ExpenseResponseDto> responseEntity = expenseController.getExpenseById(expenseId);

		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(expenseResponseDto, responseEntity.getBody());
	}


	@Test
	void createCategoryHandler_shouldReturnCreatedResponse() {
		// Arrange
		ExpenseCategoryRequestDto categoryRequestDto = new ExpenseCategoryRequestDto();
		when(expenseCategoryService.createCategory(any(ExpenseCategoryRequestDto.class)))
				.thenReturn("Category created successfully");

		// Act
		ResponseEntity<String> responseEntity = expenseController.createCategoryHandler(categoryRequestDto);

		// Assert
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals("Category created successfully", responseEntity.getBody());
	}

	@Test
	void getExpensesCategory_shouldReturnListOfCategories() {
		// Arrange
		List<ExpenseCategoryResponseDto> categoryList = Collections.singletonList(new ExpenseCategoryResponseDto());
		when(expenseCategoryService.getAllCategories()).thenReturn(categoryList);

		// Act
		ResponseEntity<List<ExpenseCategoryResponseDto>> responseEntity = expenseController.getExpensesCategory();

		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(categoryList, responseEntity.getBody());
	}

	@Test
	void updateExpenseCategory_shouldReturnAcceptedResponse() {
		// Arrange
		Long categoryId = 1L;
		ExpenseCategoryRequestDto categoryRequestDto = new ExpenseCategoryRequestDto();
		when(expenseCategoryService.updateCategory(anyLong(), any(ExpenseCategoryRequestDto.class)))
				.thenReturn("Category updated successfully");

		// Act
		ResponseEntity<String> responseEntity = expenseController.updateExpenseCategory(categoryId, categoryRequestDto);

		// Assert
		assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
		assertEquals("Category updated successfully", responseEntity.getBody());
	}
}
