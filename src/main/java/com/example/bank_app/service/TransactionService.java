package com.example.bank_app.service;

import com.example.bank_app.dto.transactiondto.TransactionRequestDto;
import com.example.bank_app.dto.transactiondto.TransactionResponseDto;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    List<TransactionResponseDto> getTransactions(String date, List<String> types);

    TransactionResponseDto findTransactionById(UUID id);

}
