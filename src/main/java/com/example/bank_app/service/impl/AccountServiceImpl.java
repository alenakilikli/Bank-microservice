package com.example.bank_app.service.impl;

import com.example.bank_app.dto.accountdto.AccountRequestDto;
import com.example.bank_app.dto.accountdto.AccountResponseDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.entity.Transaction;
import com.example.bank_app.entity.enums.TransactionType;
import com.example.bank_app.exception.AccountNotExistsException;
import com.example.bank_app.exception.AccountNotFoundException;
import com.example.bank_app.exception.BalanceNotEnoughException;
import com.example.bank_app.exception.ErrorMessage;
import com.example.bank_app.mapper.AccountMapper;
import com.example.bank_app.repository.AccountRepository;
import com.example.bank_app.repository.TransactionRepository;
import com.example.bank_app.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static com.example.bank_app.service.utils.UtilAccountServiceImpl.*;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;


    @Override
    public AccountResponseDto createAccount(AccountRequestDto accountDto) {
        var account = accountMapper.dtoRequestToAccount(accountDto);
        account.setCreationDate(Instant.now());
        accountRepository.save(account);
        return accountMapper.accountToDtoResponse(account);
    }

    public List<AccountResponseDto> getAccounts(String city, String date) {

        return accountMapper.accountsToDto(getAccountsSort(city, date));
    }

    @Override
    public AccountResponseDto getAccountById(UUID id) {
        return accountMapper.accountToDtoResponse((Account) accountRepository
                .findById(id)
                .orElseThrow(() -> new AccountNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND)));
    }


    @Override
    public void update(UUID id, AccountRequestDto dto) {
        Account account = (Account) accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (dto.getFirstName() != null) {
            account.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            account.setLastName(dto.getLastName());
        }
        if (dto.getCountry() != null) {
            account.setCountry(dto.getCountry());
        }
        if (dto.getCity() != null) {
            account.setCity(dto.getCity());
        }
        if (dto.getEmail() != null) {
            account.setEmail(dto.getEmail());
        }

        account.setCreationDate(Instant.now());
        accountRepository.save(account);
    }

    @Override
    public void transfer(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {

        Account fromAccount = findAccount(String.valueOf(fromAccountId));

        Transaction fromAccountTransaction = create(fromAccountId, toAccountId, amount);
        fromAccountTransaction.setType(TransactionType.WITHDRAW);
        addTransactionToAccount(fromAccount, fromAccountTransaction);

        if (checkBalance(amount)) {
            setStatusApproved(fromAccountTransaction);
        } else {
            setStatusDenied(fromAccountTransaction);
            throw new BalanceNotEnoughException(ErrorMessage.BALANCE_SHOULD_BE_GREATER_THAN_AMOUNT_OF_SENDING_MONEY);
        }

        Account toAccount = findAccount(String.valueOf(toAccountId));

        Transaction toAccountTransaction = create(toAccountId, fromAccountId, amount);
        setStatusApproved(toAccountTransaction);
        toAccountTransaction.setType(TransactionType.DEPOSIT);
        addTransactionToAccount(fromAccount, fromAccountTransaction);

        decreaseBalance(amount, fromAccount);
        increaseBalance(amount, toAccount);


        transactionRepository.save(fromAccountTransaction);
        transactionRepository.save(toAccountTransaction);

    }

    private List<Account> getAccountsSort(String city, String date) {
        List<Account> accounts;
        if (date != null) {
            accounts = accountRepository.findAllByCreationDate(Instant.parse(date));
        } else if (city != null) {
            accounts = accountRepository.findAllByCityIgnoreCase(city);
        } else accounts = accountRepository.findAll();

        return accounts;
    }

    private Account findAccount(String accountId) {
        var acc = accountRepository.findAccountById(UUID.fromString(accountId));
        if (acc == null) {
            throw new AccountNotExistsException(ErrorMessage.ACCOUNT_SHOULD_NOT_BE_NULL);
        }
        return acc;
    }


}



