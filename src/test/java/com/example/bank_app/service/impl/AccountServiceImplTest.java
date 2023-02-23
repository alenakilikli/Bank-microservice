package com.example.bank_app.service.impl;

import com.example.bank_app.dto.accountdto.AccountRequestDto;
import com.example.bank_app.dto.accountdto.AccountResponseDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.exception.AccountNotFoundException;
import com.example.bank_app.mapper.AccountMapper;
import com.example.bank_app.repository.AccountRepository;
import com.example.bank_app.repository.TransactionRepository;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private AccountMapper mapper;
    @Mock
    private AccountServiceImpl service;


    @DisplayName("should create account ")
    @Test
    void testAccountCreate() {
        Account account = Account.builder()
                .id(UUID.fromString("999f4567-e89b-12d3-a456-426655448800"))
                .email("alp@gmail.com")
                .creationDate(Instant.parse("2022-02-15T18:35:24.00Z"))
                .firstName("Andrew")
                .lastName("Makarow")
                .country("Germany")
                .city("Munchen")
                .amountOfMoney(BigDecimal.valueOf(1000))
                .build();
        var response = AccountResponseDto.builder()
                .id("999f4567-e89b-12d3-a456-426655448800")
                .email("alp@gmail.com")
                .creationDate("2022-02-15T18:35:24.00Z")
                .firstName("Andrew")
                .lastName("Makarow")
                .country("Germany")
                .city("Munchen")
                .amountOfMoney(BigDecimal.valueOf(2000))
                .build();
        var request = AccountRequestDto.builder()
                .id("999f4567-e89b-12d3-a456-426655448800")
                .email("alp@gmail.com")
                .firstName("Andrew")
                .lastName("Makarow")
                .country("Germany")
                .city("Munchen")
                .amountOfMoney(BigDecimal.valueOf(1000))
                .build();
        when(mapper.dtoRequestToAccount(request))
                .thenReturn(account);
        when(accountRepository.save(isA(Account.class))).thenReturn(account);
        when(mapper.accountToDtoResponse(account)).thenReturn(response);
        when(accountRepository.save(ArgumentMatchers.argThat(
                savedAccount -> savedAccount.getId().equals(account.getId())
                        && savedAccount.getFirstName().equals(account.getFirstName())
                        && savedAccount.getLastName().equals(account.getLastName())
                        && savedAccount.getCountry().equals(account.getCountry())
                        && savedAccount.getCity().equals(account.getCity())
                        && savedAccount.getEmail().equals(account.getEmail())
                        && savedAccount.getCreationDate().equals(account.getCreationDate())
                        && savedAccount.getAmountOfMoney().equals(account.getAmountOfMoney())
        )))
                .thenReturn(account);

        service.createAccount(request);
        Mockito.verify(accountRepository).save(account);

    }

    @DisplayName("method getAccountById should throw AccountNotFoundException")
    @Test
    void testThrowAccountNotExistsException() {
        Account account = EntityCreator.getAccount1();

        assertThrows(AccountNotFoundException.class, () -> service.getAccountById(String.valueOf(account.getId())));
    }


    @DisplayName("should get account according id")
    @Test
    void testGetAccountById() {
        UUID requestedId = UUID.randomUUID();
        Account account = EntityCreator.getAccount1();
        account.setId(requestedId);
        AccountResponseDto expectedResponse = DtoCreator.getAccountResponseDto();

        Mockito
                .when(accountRepository.findById(requestedId))
                .thenReturn(Optional.of(account));
        Mockito
                .when(mapper.accountToDtoResponse(account))
                .thenReturn(expectedResponse);
        AccountResponseDto actualResponseDto = service.getAccountById(String.valueOf(requestedId));
        Assertions.assertEquals(expectedResponse, actualResponseDto);
    }

    @Test
    public void testGetAccountById_existingAccount() {

        Account account = EntityCreator.getAccount1();
        UUID id = account.getId();
        account.setId(id);

        AccountResponseDto accountResponseDto = new AccountResponseDto();

        when(mapper.accountToDtoResponse(account)).thenReturn(accountResponseDto);
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));

        AccountResponseDto result = service.getAccountById(account.getId().toString());

        // Assert
        verify(accountRepository, times(1)).findById(account.getId().toString());
        verify(mapper, times(1)).accountToDtoResponse(account);
        assertEquals(accountResponseDto, result);
    }


}
