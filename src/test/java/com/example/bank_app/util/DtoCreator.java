package com.example.bank_app.util;

import com.example.bank_app.dto.accountdto.AccountRequestDto;
import com.example.bank_app.dto.accountdto.AccountResponseDto;
import com.example.bank_app.dto.transactiondto.TransactionRequestDto;
import com.example.bank_app.dto.transactiondto.TransactionResponseDto;
import com.example.bank_app.entity.enums.TransactionStatus;
import com.example.bank_app.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

public class DtoCreator {

    public static AccountRequestDto getAccountRequestDto() {
        return AccountRequestDto.builder()
                .id("999f4567-e89b-12d3-a456-426655448800")
                .email("alp@gmail.com")
                //.creationDate("2022-02-15T18:35:24.00Z")
                .firstName("Andrew")
                .lastName("Makarow")
                .country("Germany")
                .city("Munchen")
                .amountOfMoney(BigDecimal.valueOf(1000))
                .build();
    }

    public static AccountResponseDto getAccountResponseDto() {
        return AccountResponseDto.builder()
                .email("alp@gmail.com")
                .creationDate("2022-02-15T18:35:24.00Z")
                .firstName("Andrew")
                .lastName("Makarow")
                .country("Germany")
                .city("Munchen")
                .amountOfMoney(BigDecimal.valueOf(2000))
                .build();
    }

    public static TransactionResponseDto getTransactionResponseDto() {
        return TransactionResponseDto.builder()
                .dateTime(Instant.parse(String.valueOf(Instant.now())))
                .amount(BigDecimal.valueOf(100000))
                .accountFrom("Acc1")
                .accountTo("Acc2")
                .build();
    }

    public static TransactionRequestDto getTransactionRequestDto() {
        return TransactionRequestDto.builder()
                .id("899f4567-e89b-12d3-a456-426655448800")
                .type(TransactionType.BALANCE)
                .status(TransactionStatus.PENDING)
                //.dateTime(Instant.parse(String.valueOf(Instant.now())))
                .amount(BigDecimal.valueOf(100000))
                .accountFrom("999f4567-e89b-12d3-a456-426655448800")
                .accountTo("998f4567-e89b-12d3-a456-426655448800")
                .build();
    }
}
