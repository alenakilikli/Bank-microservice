package com.example.bank_app.service.impl;

import com.example.bank_app.dto.accountdto.AccountRequestDto;
import com.example.bank_app.dto.accountdto.AccountResponseDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.mapper.AccountMapper;
import com.example.bank_app.repository.AccountRepository;
import com.example.bank_app.service.AccountService;
import com.example.bank_app.util.DtoCreator;
import com.example.bank_app.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountMapper accountMapper;
    @Mock
    private AccountService accountService;

    @Test
    @DisplayName("Get account should return an AccountDTO for an existing account")
    void createAccount() {

        Account account1 = EntityCreator.getAccount1();
        AccountResponseDto responseDto1 = DtoCreator.getAccountResponseDto1();
        AccountRequestDto requestDto1 = DtoCreator.getAccountRequestDto1();
        when(accountMapper.accountToDtoResponse(account1)).thenReturn(responseDto1);
        when(accountRepository.save(account1)).thenReturn(account1);
        when(accountMapper.accountToDtoResponse(account1)).thenReturn(responseDto1);

        AccountResponseDto savedAccountDTO = accountService.createAccount(requestDto1);
        assertEquals(responseDto1, savedAccountDTO);
        verify(accountRepository).save(account1);
    }
}
