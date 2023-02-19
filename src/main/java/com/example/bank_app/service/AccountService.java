package com.example.bank_app.service;

import com.example.bank_app.dto.accountdto.AccountRequestDto;
import com.example.bank_app.dto.accountdto.AccountResponseDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    AccountResponseDto createAccount(AccountRequestDto accountRequestDto);

    List<AccountResponseDto> getAccounts(String city, String date);

    AccountResponseDto getAccountById(String id);

    void update(UUID id, AccountRequestDto dto);

    void transfer( UUID fromAccount, UUID toAccount, BigDecimal amount);

}