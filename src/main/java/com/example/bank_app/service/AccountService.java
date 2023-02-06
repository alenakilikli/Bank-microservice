package com.example.bank_app.service;

import com.example.bank_app.dto.accountDto.AccountRequestDto;
import com.example.bank_app.entity.Account;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public interface AccountService {

    AccountRequestDto createAccount(AccountRequestDto acountRequestDto);

    List<AccountRequestDto> getAccounts(String city, Instant date, Pageable pageable);

    AccountRequestDto findAccountById(Long id);

    void update(Long id, AccountRequestDto dto);

    void transfer(Account idFrom, Account idTo, BigDecimal amount);
}
