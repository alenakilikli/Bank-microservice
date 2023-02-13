package com.example.bank_app.dto.transactionDto;

import com.example.bank_app.entity.enums.TransactionStatus;
import com.example.bank_app.validation.annotation.Uuid;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Instant dateTime;

    private TransactionStatus status;

    private BigDecimal amount;

    private String accountFrom;

    private String accountTo;

}