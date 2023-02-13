package com.example.bank_app.dto.accountDto;

import com.example.bank_app.entity.Transaction;
import com.example.bank_app.validation.annotation.Uuid;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class AccountRequestDto {

    @Uuid
    private String id;

    private String email;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String creationDate;

    private String firstName;

    private String lastName;

    private String country;

    private String city;

    private BigDecimal amountOfMoney;

    private List<Transaction> transactions;
}
