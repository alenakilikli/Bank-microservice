package com.example.bank_app.mapper;

import com.example.bank_app.dto.transactionDto.TransactionResponseDto;
import com.example.bank_app.entity.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionResponseDto transactionToDto(Transaction transaction);

    Transaction dtoToTransaction(TransactionResponseDto transactionResponseDto);

    List<TransactionResponseDto> transactionsToDto(List<Transaction> transactions);
}
