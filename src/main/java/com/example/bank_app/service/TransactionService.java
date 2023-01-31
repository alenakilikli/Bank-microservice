package com.example.bank_app.service;

import com.example.bank_app.dto.accountDto.AccountRequestDto;
import com.example.bank_app.dto.transactionDto.TransactionResponseDto;

import java.util.List;

public interface TransactionService {
    List<TransactionResponseDto> getTransaction();

    TransactionResponseDto findTransactionById(Long id);
}
