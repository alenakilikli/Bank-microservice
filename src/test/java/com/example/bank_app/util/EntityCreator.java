package com.example.bank_app.util;

import com.example.bank_app.entity.Account;
import com.example.bank_app.entity.Transaction;
import com.example.bank_app.entity.enums.TransactionStatus;
import com.example.bank_app.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class EntityCreator {

    public static Account getAccount1() {
        return Account.builder()
                .id(UUID.fromString("999f4567-e89b-12d3-a456-426655448800"))
                .email("alp@gmail.com")
                .creationDate(Instant.parse("2022-02-15T18:35:24.00Z"))
                .firstName("Andrew")
                .lastName("Makarow")
                .country("Germany")
                .city("Munchen")
                .amountOfMoney(BigDecimal.valueOf(1000))
                .build();
    }


    public static Transaction getTransaction() {
        return Transaction.builder()
                .id(UUID.fromString("899f4567-e89b-12d3-a456-426655448800"))
                .dateTime(Instant.parse("2021-02-15T18:35:24.00Z"))
                .status(TransactionStatus.PENDING)
                .type(TransactionType.BALANCE)
                .amount(new BigDecimal(100000))
                .accountFrom("66664999-3333-1111-a456-426655440000")
                .accountTo("998f4567-e89b-12d3-a456-426655448800")
                .build();
    }
}
