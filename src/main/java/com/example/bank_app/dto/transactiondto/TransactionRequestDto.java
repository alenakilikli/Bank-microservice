package com.example.bank_app.dto.transactiondto;

import com.example.bank_app.entity.enums.TransactionStatus;
import com.example.bank_app.entity.enums.TransactionType;
import com.example.bank_app.validation.annotation.Uuid;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDto {

    @Uuid
    private UUID id;

    private TransactionStatus status;

    private TransactionType type;

    private BigDecimal amount;

    private String accountFrom;

    private String accountTo;
}
