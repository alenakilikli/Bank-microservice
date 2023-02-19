package com.example.bank_app.service.impl;

import com.example.bank_app.dto.accountdto.AccountRequestDto;
import com.example.bank_app.dto.accountdto.AccountResponseDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.entity.Transaction;
import com.example.bank_app.exception.BalanceNotEnoughException;
import com.example.bank_app.exception.ErrorMessage;
import com.example.bank_app.mapper.AccountMapper;
import com.example.bank_app.repository.AccountRepository;
import com.example.bank_app.repository.TransactionRepository;
import com.example.bank_app.service.AccountService;
import com.example.bank_app.validation.annotation.Uuid;
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
        accountRepository.save(account);
        return accountMapper.accountToDtoResponse(account);
    }

    public List<AccountResponseDto> getAccounts(String city, String date) {

        return accountMapper.accountsToDto(getAccountsSort(city, date));
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
    public void transfer(UUID fromAccountId,  UUID toAccountId, BigDecimal amount) {

        Account fromAccount = findAccount(String.valueOf(fromAccountId));

        Transaction fromAccountTransaction = create(fromAccountId, toAccountId, amount);
        addTransactionToAccount(fromAccount, fromAccountTransaction);

        if (checkBalance(amount)) {
            setStatusApproved(fromAccountTransaction);
        } else {
            setStatusDenied(fromAccountTransaction);
            throw new BalanceNotEnoughException(ErrorMessage.BALANCE_SHOULD_BE_GREATER_THAN_AMOUNT_OF_SENDING_MONEY);
        }

        Account toAccount = findAccount(String.valueOf(toAccountId));

        Transaction toAccountTransaction = create(fromAccountId, toAccountId, amount);
        setStatusApproved(toAccountTransaction);
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
            accounts = accountRepository.findAllByCityIgnoreCaseOrderByCreationDate(city);
        } else accounts = accountRepository.findAll();

        return accounts;
    }

    private Account findAccount(String accountId) {
        var acc=accountRepository.findAccountById(UUID.fromString(accountId));
        if(acc==null){
         throw new RuntimeException(ErrorMessage.ACCOUNT_SHOULD_NOT_BE_NULL);
        }
        return acc;
    }


}



