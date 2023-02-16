package com.example.bank_app.service.impl;

import com.example.bank_app.dto.accountdto.AccountRequestDto;
import com.example.bank_app.dto.accountdto.AccountResponseDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.entity.Transaction;
import com.example.bank_app.entity.enums.TransactionStatus;
import com.example.bank_app.entity.enums.TransactionType;
import com.example.bank_app.exception.AccountNotExistsException;
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

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public AccountResponseDto createAccount(AccountRequestDto accountDto) {
        Account account = accountMapper.dtoRequestToAccount(accountDto);
        accountRepository.save(account);

        return accountMapper.accountToDtoResponse(account);
    }


    public List<AccountResponseDto> getAccounts(String city, String date) {
        return accountMapper.accountsToDto(getAccountsSort(city, date));
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
    public AccountResponseDto getAccountById(String id) {
        return accountMapper.accountToDtoResponse(findAccount(id));
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
        account.setAmountOfMoney(dto.getAmountOfMoney());
        accountRepository.save(account);
    }

    @Override
    public void transfer(String fromAccountId, String toAccountId, BigDecimal amount) {

        Account fromAccount = findAccount(fromAccountId);

        Transaction fromAccountTransaction = createTransaction(fromAccountId, toAccountId, amount);
        addTransactionToAccount(fromAccount, fromAccountTransaction);

        if (checkBalance(amount)) {
            setStatusApproved(fromAccountTransaction);
        } else {
            setStatusDenied(fromAccountTransaction);
            throw new BalanceNotEnoughException(ErrorMessage.BALANCE_SHOULD_BE_GREATER_THAN_AMOUNT_OF_SENDING_MONEY);
        }
        Account toAccount = findAccount(fromAccountId);

        Transaction toAccountTransaction = createTransaction(fromAccountId, toAccountId, amount);
        setStatusApproved(toAccountTransaction);
        addTransactionToAccount(fromAccount, fromAccountTransaction);

        decreaseBalance(amount, fromAccount);
        increaseBalance(amount, toAccount);

        transactionRepository.save(fromAccountTransaction);
        transactionRepository.save(toAccountTransaction);

    }


    private Account findAccount(String accountId) {
        return accountRepository.findAccountById(accountId);//.orElseThrow(() -> new AccountNotExistsException(ErrorMessage.ACCOUNT_SHOULD_NOT_BE_NULL));
    }

    private Transaction createTransaction(String fromAccountId, String toAccountId, BigDecimal amount) {
       Transaction transaction = Transaction.builder()
                .dateTime(Instant.now())
                .type(TransactionType.BALANCE)
                .status(TransactionStatus.PENDING)
                .amount(amount)
                .accountFrom(fromAccountId)
                .accountTo(toAccountId)
                .build();
       transactionRepository.save(transaction);
       return transaction;
    }


    private boolean checkBalance(BigDecimal amount) {
        return amount.compareTo(new BigDecimal(0)) > 0;

    }
    private static void decreaseBalance(BigDecimal amount, Account account) {
        BigDecimal fromAccountBalance = account.getAmountOfMoney().subtract(amount);
        account.setAmountOfMoney(fromAccountBalance);
    }

    private static void increaseBalance(BigDecimal amount, Account account) {
        BigDecimal fromAccountBalance = account.getAmountOfMoney().add(amount);
        account.setAmountOfMoney(fromAccountBalance);
    }

    private static void setStatusApproved(Transaction fromTransaction) {
        fromTransaction.setStatus(TransactionStatus.APPROVED);
    }

    private static void setStatusDenied(Transaction fromTransaction) {
        fromTransaction.setStatus(TransactionStatus.DENIED);
    }

    private static void addTransactionToAccount(Account fromAccount, Transaction fromAccountTransaction) {
        fromAccount.getTransactions().add(fromAccountTransaction);
    }


}



