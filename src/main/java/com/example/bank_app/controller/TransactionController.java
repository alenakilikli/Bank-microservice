package com.example.bank_app.controller;

import com.example.bank_app.dto.transactionDto.TransactionResponseDto;
import com.example.bank_app.service.TransactionService;
import com.example.bank_app.validation.annotation.Uuid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponseDto createTransaction(TransactionResponseDto transactionResponseDto) {
        return transactionService.createTransaction(transactionResponseDto);
    }


    @GetMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionResponseDto> getTransactions(@RequestParam(value = "date", required = false) String date,
                                                        @RequestParam(value = "type", required = false) String type) {
        return transactionService.getTransaction(date, type);
    }

    @GetMapping("/transactions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponseDto showAccountById(@Uuid @PathVariable Long id) {
        return transactionService.findTransactionById(id);
    }

}
