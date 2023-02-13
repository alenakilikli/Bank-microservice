package com.example.bank_app.service;

import com.example.bank_app.dto.transactionDto.TransactionRequestDto;
import com.example.bank_app.dto.transactionDto.TransactionResponseDto;

import java.util.List;

public interface TransactionService {

    TransactionResponseDto createTransaction(TransactionRequestDto transactionResponseDto);

    List<TransactionResponseDto> getTransaction(String date, String type);

    TransactionResponseDto findTransactionById(Long id);
}
