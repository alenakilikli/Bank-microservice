package com.example.bank_app.mapper;

import com.example.bank_app.dto.accountdto.AccountRequestDto;
import com.example.bank_app.dto.accountdto.AccountResponseDto;
import com.example.bank_app.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountResponseDto accountToDto(Account account);

    Account dtoToAccount(AccountRequestDto accountRequestDto);

    List<AccountResponseDto> accountsToDto(List<Account> accounts);
}
