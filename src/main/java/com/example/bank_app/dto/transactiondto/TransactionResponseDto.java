package com.example.bank_app.dto.transactiondto;

import com.example.bank_app.entity.enums.TransactionStatus;
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