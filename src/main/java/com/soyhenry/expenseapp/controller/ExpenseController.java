package com.soyhenry.expenseapp.controller;

import com.soyhenry.expenseapp.dto.request.ExpenseCategoryRequestDto;
import com.soyhenry.expenseapp.dto.request.ExpenseRequestDto;
import com.soyhenry.expenseapp.dto.response.ExpenseCategoryResponseDto;
import com.soyhenry.expenseapp.dto.response.ExpenseResponseDto;
import com.soyhenry.expenseapp.exception.DAOException;
import com.soyhenry.expenseapp.service.ExpenseCategoryService;
import com.soyhenry.expenseapp.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {
    public final ExpenseService expenseService;
    public final ExpenseCategoryService expenseCategoryService;

    public ExpenseController(ExpenseService expenseService, ExpenseCategoryService expenseCategoryService) {
        this.expenseService = expenseService;
        this.expenseCategoryService = expenseCategoryService;
    }


    @PostMapping
    public ResponseEntity<String> createExpenseHandler(@RequestBody ExpenseRequestDto expenseRequestDto) throws DAOException {
        String response= expenseService.createExpense(expenseRequestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @GetMapping
    public ResponseEntity<List<ExpenseResponseDto>> getExpenses(){
        List<ExpenseResponseDto> response= expenseService.getAllExpenses();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateExpense(@PathVariable Long id,
                                                @RequestBody ExpenseRequestDto expenseRequestDto){
        String response= expenseService.updateExpense(id, expenseRequestDto);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) throws DAOException {
        expenseService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.GONE).body("Se elimino el gasto con id:"+id);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> getExpenseById(@PathVariable Long id){
        ExpenseResponseDto expenseResponseDto=expenseService.getExpenseById(id);
        System.out.println("ExpenseController: Obteniendo el gasto con id:"+ id);
        return  ResponseEntity.status(HttpStatus.OK).body(expenseResponseDto);
    }


    @PostMapping("/category")
    public ResponseEntity<String> createCategoryHandler(@RequestBody ExpenseCategoryRequestDto expenseCategoryRequestDto){
        String response= expenseCategoryService.createCategory(expenseCategoryRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/category")
    public ResponseEntity<List<ExpenseCategoryResponseDto>> getExpensesCategory(){
        List<ExpenseCategoryResponseDto> response= expenseCategoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PutMapping("/category/{id}")
    public ResponseEntity<String> updateExpenseCategory(@PathVariable Long id,
                                                        @RequestBody ExpenseCategoryRequestDto expenseCategoryRequestDto){
        String response = expenseCategoryService.updateCategory(id, expenseCategoryRequestDto);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
