package com.example.bank_app.service.impl;

import com.example.bank_app.dto.accountdto.AccountRequestDto;
import com.example.bank_app.dto.accountdto.AccountResponseDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.exception.AccountNotExistsException;
import com.example.bank_app.mapper.AccountMapper;
import com.example.bank_app.mapper.AccountMapperImpl;
import com.example.bank_app.repository.AccountRepository;
import com.example.bank_app.util.DtoCreator;
import com.example.bank_app.util.EntityCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    Account account;

    AccountRequestDto requestDto;

    AccountResponseDto responseDto;

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountMapper mapper = new AccountMapperImpl();
    @InjectMocks
    private AccountServiceImpl service;

    @BeforeEach
    void init() {
        Account account = EntityCreator.getAccount();
        AccountRequestDto requestDto = DtoCreator.getAccountRequestDto();
        AccountResponseDto responseDto = DtoCreator.getAccountResponseDto();
    }


    @DisplayName("test create Account")
    @Test
    void testCreateAccount() {
        var isAccountCreated = service.createAccount(requestDto);
        assertEquals(isAccountCreated,mapper.accountToDtoResponse(account));
        verify(accountRepository,times(1)).save(account);

    }

    @DisplayName("test account not exists exeption")
    @Test
    void testThrowAccountNotExistsException() {

        assertThrows(AccountNotExistsException.class, () -> service.createAccount(requestDto));
    }

    @Test
    void testGetAccountById() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        List<AccountResponseDto> responsesDto = new ArrayList();
        responsesDto.add(responseDto);

        AccountResponseDto actual = service.getAccountById(String.valueOf(account.getId()));
        System.out.println(actual);
        Assertions.assertEquals(actual, responseDto);
    }

    @Test
    void testTransfer() {

    }
}
