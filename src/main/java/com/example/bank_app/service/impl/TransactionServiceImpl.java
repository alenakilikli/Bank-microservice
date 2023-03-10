package com.example.bank_app.service.impl;

import com.example.bank_app.dto.transactiondto.TransactionResponseDto;
import com.example.bank_app.entity.Transaction;
import com.example.bank_app.mapper.TransactionMapper;
import com.example.bank_app.repository.AccountRepository;
import com.example.bank_app.repository.TransactionRepository;
import com.example.bank_app.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;


    @Override
    public List<TransactionResponseDto> getTransactions(String date, List<String> types) {
        List<Transaction> transactions = transactionRepository.findAll();

        if (date != null) {
            Instant instant = Instant.parse(date);
            transactions = transactions.stream()
                    .filter(transaction -> transaction.getDateTime().equals(instant))
                    .toList();
        }

        if (types != null) {
            transactions = transactions.stream()
                    .filter(transaction -> types.contains(transaction.getType().toString()))
                    .toList();
        }


        return transactionMapper.transactionsToDto(transactions);
    }


    @Override
    public TransactionResponseDto findTransactionById(UUID id) {
        Transaction transaction = transactionRepository.findById((id)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return transactionMapper.transactionToDto(transaction);
    }

}
