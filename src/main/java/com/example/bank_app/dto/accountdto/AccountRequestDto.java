package com.example.bank_app.dto.accountdto;

import com.example.bank_app.entity.Transaction;
import com.example.bank_app.validation.annotation.Uuid;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class AccountRequestDto {

    @Uuid
    private String id;

    private String email;

    private String firstName;

    private String lastName;

    private String country;

    private String city;

    private BigDecimal amountOfMoney;

}
