package com.example.bank_app.util;

import com.example.bank_app.dto.accountdto.AccountRequestDto;
import com.example.bank_app.dto.accountdto.AccountResponseDto;
import com.example.bank_app.dto.transactiondto.TransactionRequestDto;
import com.example.bank_app.dto.transactiondto.TransactionResponseDto;
import com.example.bank_app.entity.enums.TransactionStatus;
import com.example.bank_app.entity.enums.TransactionType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder
public class DtoCreator {

    public static AccountRequestDto getAccountRequestDto1() {
        return AccountRequestDto.builder()
                .id("999f4567-e89b-12d3-a456-426655448800")
                .email("alp@gmail.com")
                .firstName("Andrew")
                .lastName("Makarow")
                .country("Germany")
                .city("Munchen")
                .amountOfMoney(BigDecimal.valueOf(1000))
                .build();
    }

    public static AccountResponseDto getAccountResponseDto1() {
        return AccountResponseDto.builder()
                .id("999f4567-e89b-12d3-a456-426655448800")
                .email("alp@gmail.com")
                .firstName("Andrew")
                .lastName("Makarow")
                .country("Germany")
                .city("Munchen")
                .amountOfMoney(BigDecimal.valueOf(1000))
                .build();
    }

    public static AccountRequestDto getAccountRequestDto2() {
        return AccountRequestDto.builder()
                .id("001f4567-e77b-12d3-a456-422255448800")
                .email("lklp@gmail.com")
                .firstName("Alla")
                .lastName("Ivanova")
                .country("Russia")
                .city("Moscow")
                .amountOfMoney(BigDecimal.valueOf(1000))
                .build();
    }

    public static AccountResponseDto getAccountResponseDto2() {
        return AccountResponseDto.builder()
                .id("001f4567-e77b-12d3-a456-422255448800")
                .email("lklp@gmail.com")
                .creationDate("2022-02-15T18:35:24.00Z")
                .firstName("Alla")
                .lastName("Ivanova")
                .country("Russia")
                .city("Russia")
                .amountOfMoney(BigDecimal.valueOf(1000))
                .build();
    }

    public static TransactionResponseDto getTransactionResponseDto1() {
        return TransactionResponseDto.builder()
                .id(UUID.fromString("899f4567-e89b-12d3-a456-426655448800"))
                .dateTime(String.valueOf(Instant.now()))
                .amount(BigDecimal.valueOf(100000))
                .accountFrom("999f4567-e89b-12d3-a456-426655448800")
                .accountTo("998f4567-e89b-12d3-a456-426655448800")
                .build();
    }

    public static TransactionRequestDto getTransactionRequestDto1() {
        return TransactionRequestDto.builder()
                .id(UUID.fromString("899f4567-e89b-12d3-a456-426655448800"))
                .type(TransactionType.BALANCE)
                .status(TransactionStatus.PENDING)
                .amount(BigDecimal.valueOf(100000))
                .accountFrom("999f4567-e89b-12d3-a456-426655448800")
                .accountTo("998f4567-e89b-12d3-a456-426655448800")
                .build();
    }

    public static TransactionResponseDto getTransactionResponseDto2() {
        return TransactionResponseDto.builder()
                .id(UUID.fromString("333k4567-e89b-12d3-a456-426655448800"))
                .dateTime(String.valueOf(Instant.now()))
                .amount(BigDecimal.valueOf(400))
                .accountFrom("999f4567-e89b-12d3-a456-426655448800")
                .accountTo("998f4567-e89b-12d3-a456-426655448800")
                .build();
    }

    public static TransactionRequestDto getTransactionRequestDto2() {
        return TransactionRequestDto.builder()
                .id(UUID.fromString("333k4567-e89b-12d3-a456-426655448800"))
                .type(TransactionType.BALANCE)
                .status(TransactionStatus.PENDING)
                .amount(BigDecimal.valueOf(400))
                .accountFrom("999f4567-e89b-12d3-a456-426655448800")
                .accountTo("998f4567-e89b-12d3-a456-426655448800")
                .build();
    }
}
