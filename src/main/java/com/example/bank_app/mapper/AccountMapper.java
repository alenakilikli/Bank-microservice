package com.example.bank_app.mapper;

import com.example.bank_app.dto.accountdto.AccountRequestDto;
import com.example.bank_app.dto.accountdto.AccountResponseDto;
import com.example.bank_app.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountResponseDto accountToDtoResponse(Account account);

    AccountRequestDto accountToDtoRequest(Account account);

    Account dtoRequestToAccount(AccountRequestDto accountRequestDto);

    Account dtoResponseToAccount(AccountResponseDto accountResponseDto);

    AccountRequestDto requestToResponse(AccountResponseDto responseDto);

    List<AccountResponseDto> accountsToDto(List<Account> accounts);
}
