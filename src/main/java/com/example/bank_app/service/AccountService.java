package com.example.bank_app.service;

import com.example.bank_app.dto.accountDto.AccountRequestDto;
import com.example.bank_app.dto.accountDto.AccountResponseDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    AccountResponseDto createAccount(AccountRequestDto accountRequestDto);

    List<AccountResponseDto> getAccounts(String city, String date);

    AccountResponseDto findAccountById(UUID id);

    void update(UUID id, AccountRequestDto dto);

    void transfer(UUID fromAccountId, UUID toAccountId, BigDecimal amount);

}