package com.example.bank_app.dto.accountdto;

import com.example.bank_app.validation.annotation.Uuid;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class AccountResponseDto {
    @Uuid
    private String id;

    private String email;

    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String creationDate;

    private String firstName;

    private String lastName;

    private String country;

    private String city;

    private BigDecimal amountOfMoney;
}
