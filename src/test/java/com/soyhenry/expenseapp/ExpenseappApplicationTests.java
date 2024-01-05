package com.soyhenry.expenseapp;

import com.soyhenry.expenseapp.controller.ExpenseController;
import com.soyhenry.expenseapp.dto.request.ExpenseRequestDto;
import com.soyhenry.expenseapp.dto.response.ExpenseResponseDto;
import com.soyhenry.expenseapp.exception.DAOException;
import com.soyhenry.expenseapp.service.ExpenseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ExpenseControllerTest {

	@Mock
	private ExpenseService expenseService;

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

		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertEquals("Expense created successfully", response.getBody());
		verify(expenseService, times(1)).createExpense(requestDto);
	}

	@Test
	public void testGetExpenses() {
		List<ExpenseResponseDto> expectedResponse = Arrays.asList(new ExpenseResponseDto(), new ExpenseResponseDto());
		when(expenseService.getAllExpenses()).thenReturn(expectedResponse);

		ResponseEntity<List<ExpenseResponseDto>> response = expenseController.getExpenses();

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(expectedResponse, response.getBody());
		verify(expenseService, times(1)).getAllExpenses();
	}

	@Test
	public void testUpdateExpense() {
		Long id = 1L;
		ExpenseRequestDto requestDto = new ExpenseRequestDto();
		when(expenseService.updateExpense(id, requestDto)).thenReturn("Expense updated successfully");

		ResponseEntity<String> response = expenseController.updateExpense(id, requestDto);

		Assertions.assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		Assertions.assertEquals("Expense updated successfully", response.getBody());
		verify(expenseService, times(1)).updateExpense(id, requestDto);
	}
}
