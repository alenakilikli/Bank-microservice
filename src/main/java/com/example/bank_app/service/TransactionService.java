package com.example.bank_app.service;

import com.example.bank_app.dto.transactiondto.TransactionRequestDto;
import com.example.bank_app.dto.transactiondto.TransactionResponseDto;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    TransactionResponseDto createTransaction(TransactionRequestDto requestDto);

    List<TransactionResponseDto> getTransactions(String date, List<String> types, String sort);

    TransactionResponseDto findTransactionById(UUID id);

    List<TransactionResponseDto>findTransactionsById(UUID id);
}
