package com.example.bank_app.service;

import com.example.bank_app.dto.accountDto.AccountRequestDto;
import com.example.bank_app.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    AccountRequestDto createAccount(AccountRequestDto acountRequestDto);

    List<AccountRequestDto> getAccount(String city, String date);

    AccountRequestDto findAccountById(Long id);

    void update(Long id, AccountRequestDto dto);

    void transfer(Account idFrom, Account idTo, BigDecimal amount);
}
