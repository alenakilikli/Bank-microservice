package com.example.bank_app.controller;

import com.example.bank_app.dto.accountDto.AccountRequestDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.service.AccountService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountRequestDto create(@RequestBody AccountRequestDto account) {
        return accountService.createAccount(account);
    }


    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountRequestDto> getAccounts(Pageable pageable,@RequestParam(value = "date", required = false) Instant date,
                                               @RequestParam(value = "city", required = false) String city) {
        return accountService.getAccounts(city, date,pageable);

    }


    @GetMapping("/accounts/{id}")
    public AccountRequestDto showAccountById(@PathVariable Long id) {
        return accountService.findAccountById(id);
    }

    @PatchMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, AccountRequestDto dto) {
        accountService.update(id, dto);
    }

    @PutMapping("/accounts/transfer")
    @ResponseStatus(HttpStatus.OK)
    public void transferMoney(@RequestParam(value = "idFrom", required = false) Account idFrom,
                              @RequestParam(value = "to", required = false) Account idTo,
                              @RequestParam(value = "amount", required = false) BigDecimal amount) {
        accountService.transfer(idFrom,idTo,amount);
    }


}
