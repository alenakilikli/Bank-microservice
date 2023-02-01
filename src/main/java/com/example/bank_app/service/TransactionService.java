package com.example.bank_app.service;

import com.example.bank_app.dto.transactionDto.TransactionResponseDto;

import java.util.List;

public interface TransactionService {

    List<TransactionResponseDto> getTransaction(String date, String type, String sort);

    TransactionResponseDto findTransactionById(Long id);
}
