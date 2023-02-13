package com.example.bank_app.service;

import com.example.bank_app.dto.transactiondto.TransactionResponseDto;

import java.util.List;

public interface TransactionService {

    List<TransactionResponseDto> getTransaction(String date, String type);

    TransactionResponseDto findTransactionById(Long id);
}
