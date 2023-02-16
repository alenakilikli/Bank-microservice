package com.example.bank_app.service.impl;

import com.example.bank_app.dto.accountdto.AccountRequestDto;
import com.example.bank_app.dto.accountdto.AccountResponseDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.exception.AccountNotExistsException;
import com.example.bank_app.mapper.AccountMapper;
import com.example.bank_app.repository.AccountRepository;
import com.example.bank_app.util.DtoCreator;
import com.example.bank_app.util.EntityCreator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountMapper mapper;
    @InjectMocks
    private AccountServiceImpl service;

    @AfterEach
    public void clearMocks() {
        clearInvocations(accountRepository, mapper);
    }

    @DisplayName("test create Account")
    @Test
    void testCreateAccount() {
        //java.lang.NullPointerException: Cannot invoke "com.example.bank_app.dto.accountdto.AccountResponseDto.getId()" because "actualDto" is null

        AccountRequestDto requestDto = DtoCreator.getAccountRequestDto();
        AccountResponseDto responseDto = DtoCreator.getAccountResponseDto();
        Account account = EntityCreator.getAccount();

        when(accountRepository.save(account)).thenReturn(account);
        when(mapper.dtoRequestToAccount(requestDto)).thenReturn(account);
       when(accountRepository.findById(UUID.fromString(requestDto.getId()))).thenReturn(Optional.of(account));
        when(mapper.accountToDtoResponse(account)).thenReturn(responseDto);

        AccountResponseDto actualDto = service.createAccount(requestDto);
        Assertions.assertEquals(actualDto.getId(), responseDto.getId());
    }

    @DisplayName("test account not exists exeption")
    @Test
    void testThrowAccountNotExistsException(){
        AccountRequestDto requestDto = DtoCreator.getAccountRequestDto();

        assertThrows(AccountNotExistsException.class,()->service.createAccount(requestDto));
    }
    @Test
    void testGetAccountById(){
        AccountResponseDto responseDto = DtoCreator.getAccountResponseDto();
        Account account = EntityCreator.getAccount();
        List<Account> accounts= new ArrayList<>();
        accounts.add(account);
        List<AccountResponseDto>responsesDto = new ArrayList();
        responsesDto.add(responseDto);

        AccountResponseDto actual = service.getAccountById(String.valueOf(account.getId()));
        Assertions.assertEquals(actual, responseDto);
    }
}
