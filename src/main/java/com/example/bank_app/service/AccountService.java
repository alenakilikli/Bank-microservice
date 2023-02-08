package com.example.bank_app.service;

import com.example.bank_app.dto.accountDto.AccountRequestDto;
import com.example.bank_app.entity.Account;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    AccountRequestDto createAccount(AccountRequestDto accountRequestDto);

    List<AccountRequestDto> getAccounts(String city, String date);

    AccountRequestDto findAccountById(UUID id);

    void update(UUID id, AccountRequestDto dto);

    void transfer(Account idFrom, Account idTo, BigDecimal amount);
}
