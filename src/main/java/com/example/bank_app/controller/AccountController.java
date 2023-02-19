package com.example.bank_app.controller;

import com.example.bank_app.dto.accountdto.AccountRequestDto;
import com.example.bank_app.dto.accountdto.AccountResponseDto;
import com.example.bank_app.service.AccountService;
import com.example.bank_app.validation.annotation.Uuid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDto create(@RequestBody AccountRequestDto account) {
        return accountService.createAccount(account);
    }


    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountResponseDto> getAccounts(@RequestParam(value = "date", required = false) String date,
                                                @RequestParam(value = "city", required = false) String city) {
        return accountService.getAccounts(date, city);

    }


    @GetMapping("/accounts/{id}")
    public AccountResponseDto showAccountById(@Uuid @PathVariable String id) {
        return accountService.getAccountById(id);
    }

    @PatchMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@Uuid @PathVariable UUID id, AccountRequestDto dto) {
        accountService.update(id, dto);
    }

    @PutMapping("/accounts/transfer")
    @ResponseStatus(HttpStatus.OK)
    public void transferMoney(@Uuid @RequestParam(value = "idFrom", required = false) UUID fromAccount,
                              @Uuid @RequestParam(value = "idTo", required = false) UUID toAccount,
                              @RequestParam(value = "amount", required = false) BigDecimal amount) {
        accountService.transfer(fromAccount, toAccount, amount);
    }


}
