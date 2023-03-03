package com.example.bank_app.service.impl;

import com.example.bank_app.dto.accountdto.AccountRequestDto;
import com.example.bank_app.dto.accountdto.AccountResponseDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.exception.AccountNotFoundException;
import com.example.bank_app.mapper.AccountMapper;
import com.example.bank_app.repository.AccountRepository;
import com.example.bank_app.util.DtoCreator;
import com.example.bank_app.util.EntityCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountMapper accountMapper;
    @Mock
    private AccountServiceImpl accountService;

}
