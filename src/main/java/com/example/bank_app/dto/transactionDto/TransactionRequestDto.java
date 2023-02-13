package com.example.bank_app.dto.transactionDto;

import com.example.bank_app.entity.enums.TransactionStatus;
import com.example.bank_app.entity.enums.TransactionType;
import com.example.bank_app.validation.annotation.Uuid;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.Instant;

public class TransactionRequestDto {

    @Uuid
    private String id;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Instant dateTime;

    private TransactionStatus status;

    private TransactionType type;

    private BigDecimal amount;

    private String accountFrom;

    private String accountTo;
}
