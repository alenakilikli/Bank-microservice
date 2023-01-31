package com.example.bank_app.controller;

import com.example.bank_app.dto.transactionDto.TransactionResponseDto;
import com.example.bank_app.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionResponseDto> getTransactions(@RequestParam(value = "date", required = false) String date,
                                                        @RequestParam(value = "type", required = false) String type,
                                                        @RequestParam(value = "sort", required = false) String sort) {
        return transactionService.getTransaction();
    }

    @GetMapping("/transactions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponseDto showAccountById(@PathVariable Long id) {
        return transactionService.findTransactionById(id);
    }

}
