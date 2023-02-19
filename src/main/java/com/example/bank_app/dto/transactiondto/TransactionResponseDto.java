package com.example.bank_app.dto.transactiondto;

import com.example.bank_app.entity.enums.TransactionStatus;
import com.example.bank_app.entity.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {

    private Instant dateTime;

    private TransactionStatus status;

    private TransactionType type;

    private BigDecimal amount;

    private String accountFrom;

    private String accountTo;

}