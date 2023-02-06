package com.example.bank_app.util;

import com.example.bank_app.entity.Account;
import com.example.bank_app.entity.Transaction;
import com.example.bank_app.entity.enums.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class EntityCreator {

    public static Account getAccount() {
        Account account = Account.builder()
                .id("66664999-3333-1111-a456-426655440000")
                .email("alp@gmail.com")
                .creationDate(Instant.parse("2022-02-15T18:35:24.00Z"))
                .firstName("alena")
                .lastName("klkl")
                .country("tr")
                .city("Istanbul")
                .amountOfMoney(new BigDecimal("30400"))
                .build();
        return account;
    }

    public static Transaction getTransaction() {
        Transaction transaction = Transaction.builder()
                .id("66664999-3333-1111-a456-426655440000")
                .dateTime(Instant.parse("2021-02-15T18:35:24.00Z"))
                .type(TransactionType.WITHDRAW)
                .amount(new BigDecimal("30400"))
                .accountFrom("acc1")
                .accountTo("acc2")
                .build();
        return transaction;
    }
}
