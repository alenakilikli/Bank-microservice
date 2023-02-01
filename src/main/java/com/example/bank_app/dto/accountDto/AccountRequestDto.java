package com.example.bank_app.dto.accountDto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

import java.time.Instant;

@RequiredArgsConstructor

@Setter
@Getter
public class AccountRequestDto {


    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Instant creationDate;

    private String firstName;

    private String lastName;

    private String country;

    private String city;
}
