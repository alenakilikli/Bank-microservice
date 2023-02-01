package com.example.bank_app.service.impl;

import com.example.bank_app.dto.accountDto.AccountRequestDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.entity.Transaction;
import com.example.bank_app.mapper.AccountMapper;
import com.example.bank_app.repository.AccountRepo;
import com.example.bank_app.repository.TransactionRepo;
import com.example.bank_app.service.AccountService;
import com.example.bank_app.utils.ValidationMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;

    private final TransactionRepo transactionRepo;
    private final AccountMapper accountMap;


    @Override
    public AccountRequestDto createAccount(AccountRequestDto accountDto) {
        Account account1 = accountMap.dtoToAccount(accountDto);
        account1.setId(account1.getId());

        accountRepo.save(account1);

        return accountMap.accountToDto(account1);
    }

    @Override
    public List<AccountRequestDto> getAccounts(String city, String date, String sort) {
        List<Account> accounts = accountRepo.findAll();
        var accountsDto = accounts.stream().sorted(Comparator.comparing(Account::getId)).toList();
        return accountMap.accountsToDto(accountsDto);
    }

    @Override
    public AccountRequestDto findAccountById(Long id) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return accountMap.accountToDto(account);
    }

    @Override
    public void update(Long id, AccountRequestDto dto) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        account.setEmail(dto.getEmail());
        account.setCreationDate(Instant.now());
        account.setFirstName(dto.getFirstName());
        account.setLastName(dto.getLastName());
        account.setCountry(dto.getCountry());
        account.setCity(dto.getCity());
        accountRepo.save(account);
    }

    @Override
    public void transfer(Account fromAccount, Account toAccount, BigDecimal amount) {
        if (fromAccount == null || toAccount == null || amount.compareTo(new BigDecimal(0)) <= 0) {
            throw new IllegalArgumentException(ValidationMessages.FROM_ACCOUNT_AND_TO_ACCOUNT_SHOULD_NOT_BE_NULL_AND_TRANSFER_AMOUNT_SHOULD_BE_GREATER_THAN_0);
        }
        if (fromAccount.getAmountOfMoney().compareTo(amount) < 0) {
            throw new IllegalArgumentException(ValidationMessages.FROM_ACCOUNT_DOES_NOT_CONTAIN_SUFFICIENT_FUNDS);
        }


        BigDecimal fromAccountBalance = fromAccount.getAmountOfMoney().subtract(amount);
        fromAccount.setAmountOfMoney(fromAccountBalance);
        BigDecimal toAccountBalance = toAccount.getAmountOfMoney().add(amount);
        toAccount.setAmountOfMoney(toAccountBalance);

        Transaction fromAccountTransaction = new Transaction();

        Transaction toAccountTransaction = new Transaction();


        fromAccount.getTransactions().add(fromAccountTransaction);
        toAccount.getTransactions().add(toAccountTransaction);

        transactionRepo.save(fromAccountTransaction);
        transactionRepo.save(toAccountTransaction);

    }

}

