package com.example.bank_app.dto.transactionDto;

import java.math.BigDecimal;
import java.time.Instant;

public class TransactionResponseDto {
    public Instant dateTime;
    public String type;
    public BigDecimal amount;
    public String accountFrom;
    public String accountTo;
}