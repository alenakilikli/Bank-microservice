package com.example.bank_app.controller;

import com.example.bank_app.dto.transactiondto.TransactionRequestDto;
import com.example.bank_app.dto.transactiondto.TransactionResponseDto;
import com.example.bank_app.service.TransactionService;
import com.example.bank_app.validation.annotation.Uuid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionResponseDto> getTransactions(@RequestParam(value = "date", required = false) String date,
                                                        @RequestParam(value = "type", required = false) List<String> type) {
        return transactionService.getTransactions(date, type);
    }

    @GetMapping("/transactions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponseDto getById(@Uuid @PathVariable String id) {
        return transactionService.findTransactionById(UUID.fromString(id));
    }

}
