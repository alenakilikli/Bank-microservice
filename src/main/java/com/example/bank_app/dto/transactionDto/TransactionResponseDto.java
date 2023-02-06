package com.example.bank_app.dto.transactionDto;

import com.example.bank_app.entity.Account;
import com.example.bank_app.entity.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {

    private UUID id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private String dateTime;

    private TransactionType type;

    private BigDecimal amount;

    private String accountFrom;

    private String accountTo;

}