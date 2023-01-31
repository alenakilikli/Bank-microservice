package com.example.mapstruct.mapTest;

import com.example.bank_app.dto.AccountRequestDto.AccountRequestDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.mapper.AccountMap;
import com.example.bank_app.mapper.AccountMapImpl;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountMapperTest {

    private final AccountMap mapper = new AccountMapImpl();

    @Test
    public void shouldMapAccountToDto() {
        Account account = new Account();
        account.setCity("aaaaaaa");

        AccountRequestDto dto = mapper.accountToDto(account);
        assertEquals(account.getCity(), dto.getCity());

    }
    @Test
    public void shouldMapDtoToAccount(){
        AccountRequestDto dto = new AccountRequestDto();
        dto.setCity("aaaa");
        Account account = mapper.dtoToAccount(dto);
        assertEquals(account.getCity(),dto.getCity());}
}
