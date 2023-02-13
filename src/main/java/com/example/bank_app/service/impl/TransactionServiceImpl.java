package com.example.bank_app.service.impl;

import com.example.bank_app.dto.transactionDto.TransactionRequestDto;
import com.example.bank_app.dto.transactionDto.TransactionResponseDto;
import com.example.bank_app.entity.Transaction;
import com.example.bank_app.mapper.TransactionMapper;
import com.example.bank_app.repository.TransactionRepository;
import com.example.bank_app.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto) {

        Transaction transaction = transactionMapper.dtoToTransaction(transactionRequestDto);

        transactionRepository.save(transaction);

        return transactionMapper.transactionToDto(transaction);
    }

    @Override
    public List<TransactionResponseDto> getTransaction(String date, String type) {
        List<Transaction> transactions = transactionRepository.findAll();
        var transactionsList = transactions.stream().toList();
        return transactionMapper.transactionsToDto(transactionsList);
    }

    @Override
    public TransactionResponseDto findTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return transactionMapper.transactionToDto(transaction);
    }
}
