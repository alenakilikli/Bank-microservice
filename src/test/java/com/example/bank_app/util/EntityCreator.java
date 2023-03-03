package com.example.bank_app.util;

import com.example.bank_app.entity.Account;
import com.example.bank_app.entity.Transaction;
import com.example.bank_app.entity.enums.TransactionStatus;
import com.example.bank_app.entity.enums.TransactionType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
@Builder
public class EntityCreator {
    public static Account getAccount1() {
        Account account1 = new Account();
        account1.setId(UUID.fromString("d8a6a52c-8361-4d51-8a8d-fcdd2f7f26b9"));
        account1.setCreationDate(Instant.parse("2022-02-15T18:35:24.00Z"));
        account1.setEmail("john.doe@example.com");
        account1.setFirstName("John");
        account1.setLastName("Doe");
        account1.setCountry("US");
        account1.setCity("New York");
        account1.setAmountOfMoney(BigDecimal.valueOf(10021.00));
        return account1;
    }

//    public static Account getAccount1() {
//        Account account = Account.builder()
//                .id(UUID.fromString("999f4567-e89b-12d3-a456-426655448800"))
//                .email("alp@gmail.com")
//                .creationDate(Instant.parse("2022-02-15T18:35:24.00Z"))
//                .firstName("Andrew")
//                .lastName("Makarow")
//                .country("Germany")
//                .city("Munchen")
//                .amountOfMoney(BigDecimal.valueOf(1000))
//                .build();
//        return account;
//    }

    public static Account getAccount2() {
        return Account.builder()
                .id(UUID.fromString("12d72b50-1c7b-4f45-9bc1-ea9e9a95c8d5"))
                .email("does.doe@example.com")
                .creationDate(Instant.parse("2022-02-15T18:35:24.00Z"))
                .firstName("Emma")
                .lastName("Watson")
                .country("England")
                .city("London")
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

    public static Transaction getTransaction2() {
        return Transaction.builder()
                .id(UUID.fromString("098f4567-e89b-12d3-a456-426655446475"))
                .dateTime(Instant.parse("2021-02-15T18:35:24.00Z"))
                .status(TransactionStatus.PENDING)
                .type(TransactionType.BALANCE)
                .amount(new BigDecimal(500000))
                .accountFrom("12d72b50-1c7b-4f45-9bc1-ea9e9a95c8d5")
                .accountTo("998f4567-e89b-12d3-a456-426655448800")
                .build();
    }
}
