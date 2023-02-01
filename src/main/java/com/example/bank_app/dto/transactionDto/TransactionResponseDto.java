package com.example.bank_app.dto.transactionDto;

import com.example.bank_app.entity.type.TransactionType;
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
    public Instant dateTime;

    public TransactionType type;

    public BigDecimal amount;

    public String accountFrom;

    public String accountTo;
}