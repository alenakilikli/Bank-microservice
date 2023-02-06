package com.example.bank_app.mapper;

import com.example.bank_app.dto.accountDto.AccountRequestDto;
import com.example.bank_app.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountRequestDto accountToDto(Account account);

    Account dtoToAccount(AccountRequestDto accountRequestDto);

    List<AccountRequestDto> accountsToDto(List<Account> accounts);
}
