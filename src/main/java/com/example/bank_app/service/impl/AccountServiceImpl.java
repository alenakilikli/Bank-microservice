package com.example.bank_app.service.impl;

import com.example.bank_app.dto.accountDto.AccountRequestDto;
import com.example.bank_app.dto.accountDto.AccountResponseDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.entity.Transaction;
import com.example.bank_app.entity.enums.TransactionStatus;
import com.example.bank_app.entity.enums.TransactionType;
import com.example.bank_app.exception.ValidationMessages;
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

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public AccountResponseDto createAccount(AccountRequestDto accountDto) {
        Account account = accountMapper.dtoToAccount(accountDto);
        account.setTransactions(null);
        accountRepository.save(account);

        return accountMapper.accountToDto(account);
    }

    public List<AccountResponseDto> getAccounts(String city, String date) {
        return accountMapper.accountsToDto(getAccountsSort(date, city));
    }

    private List<Account> getAccountsSort(String city, String date) {
        List<Account> accounts;

        if (date != null) {
            accounts = accountRepository.findAllByCreationDate(date);
        } else if (city != null) {
            accounts = accountRepository.findAllByCityIgnoreCaseOrderByCreationDate(city);
        } else accounts = accountRepository.findAll();

        return accounts;
    }


    @Override
    public AccountResponseDto findAccountById(UUID id) {
        Account account = (Account) accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return accountMapper.accountToDto(account);
    }

    @Override
    public void update(UUID id, AccountRequestDto dto) {
        Account account = (Account) accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        account.setEmail(dto.getEmail());
        account.setCreationDate(Instant.now());
        account.setFirstName(dto.getFirstName());
        account.setLastName(dto.getLastName());
        account.setCountry(dto.getCountry());
        account.setCity(dto.getCity());
        accountRepository.save(account);
    }

    @Override
    public void transfer(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {
//if fromAccount not exits - exception
        Account fromAccount = (Account) accountRepository.findById(fromAccountId).orElseThrow(() -> new IllegalArgumentException(ValidationMessages.FROM_ACCOUNT_AND_TO_ACCOUNT_SHOULD_NOT_BE_NULL));
//if toAccount not exits - exception
        Account toAccount = (Account) accountRepository.findById(toAccountId).orElseThrow(() -> new IllegalArgumentException(ValidationMessages.FROM_ACCOUNT_AND_TO_ACCOUNT_SHOULD_NOT_BE_NULL));
//creating transaction for fromAccount and save to list of transactions of fromAccount
        Transaction fromAccountTransaction = new Transaction();
        createTransactionWithdraw(fromAccountId, toAccountId, amount);
        fromAccount.getTransactions().add(fromAccountTransaction);

        Transaction toAccountTransaction = null;

//check balance of fromAccount
        //if enough create transaction for toAccount
        //and save transaction fo toAccount
        // change balances on both accounts
        if (checkBalance(amount, fromAccountTransaction)) {

            toAccountTransaction = new Transaction();
            fromAccountTransaction.setStatus(TransactionStatus.APPROVED);
            createTransactionDeposit(fromAccountId, toAccountId, amount);
            toAccount.getTransactions().add(toAccountTransaction);
        }

        BigDecimal fromAccountBalance = fromAccount.getAmountOfMoney().subtract(amount);
        fromAccount.setAmountOfMoney(fromAccountBalance);

        BigDecimal toAccountBalance = toAccount.getAmountOfMoney().add(amount);
        toAccount.setAmountOfMoney(toAccountBalance);

        transactionRepository.save(fromAccountTransaction);
        assert toAccountTransaction != null;
        transactionRepository.save(toAccountTransaction);

    }

    private void createTransactionWithdraw(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {
        Transaction.builder()
                .dateTime(Instant.now())
                .type(TransactionType.WITHDRAW)
                .status(TransactionStatus.APPROVED)
                .amount(amount)
                .accountFrom(String.valueOf(fromAccountId))
                .accountTo(String.valueOf(toAccountId))
                .build();

    }

    private void createTransactionDeposit(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {
        Transaction.builder()
                .dateTime(Instant.now())
                .type(TransactionType.DEPOSIT)
                .status(TransactionStatus.APPROVED)
                .amount(amount)
                .accountFrom(String.valueOf(fromAccountId))
                .accountTo(String.valueOf(toAccountId))
                .build();
    }

    private boolean checkBalance(BigDecimal amount, Transaction fromTransaction) {

        if (amount.compareTo(new BigDecimal(0)) <= 0) {
            fromTransaction.setStatus(TransactionStatus.DENIED);
            throw new IllegalArgumentException(ValidationMessages.FROM_ACCOUNT_AND_TO_ACCOUNT_SHOULD_NOT_BE_NULL_AND_TRANSFER_AMOUNT_SHOULD_BE_GREATER_THAN_0);
        } else {
            return true;

        }

    }


}



