package com.example.bank_app.util;

import com.example.bank_app.dto.accountDto.AccountRequestDto;
import com.example.bank_app.dto.transactionDto.TransactionResponseDto;
import com.example.bank_app.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

public class DtoCreator {

    public static AccountRequestDto getAccountDto(){
        return AccountRequestDto.builder()
                .email("alp@gmail.com")
                .creationDate(Instant.now())
                .firstName("Andrew")
                .lastName("Makarow")
                .country("Germany")
                .city("Munchen")
                .build();
    }
    public static TransactionResponseDto getTransactionDto(){
        return TransactionResponseDto.builder()
                .dateTime(Instant.now())
                .type(String.valueOf(TransactionType.WITHDRAW))
                .amount(BigDecimal.valueOf(100000))
                .accountFrom("Acc1")
                .accountTo("Acc2")
                .build();
    }
}
