package com.soyhenry.expenseapp.controller;

import com.soyhenry.expenseapp.dto.request.ExpenseRequestDto;
import com.soyhenry.expenseapp.dto.response.ExpenseResponseDto;
import com.soyhenry.expenseapp.exception.DAOException;
import com.soyhenry.expenseapp.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {
    public final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
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
    public ResponseEntity<String> deleteExpense(@PathVariable Long id){
        //expenseService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.GONE).body("Se elimino el gasto con id:"+id);
    }

}
