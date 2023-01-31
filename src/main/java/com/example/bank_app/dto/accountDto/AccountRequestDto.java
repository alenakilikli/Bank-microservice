package com.example.bank_app.dto.accountDto;

import com.example.bank_app.entity.Transaction;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
@Builder
@RequiredArgsConstructor
public class AccountRequestDto {

    private String email;
    private Instant creationDate;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private List<Transaction> transactions;
}
