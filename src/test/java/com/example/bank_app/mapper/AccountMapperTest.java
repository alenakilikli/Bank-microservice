package com.example.bank_app.mapper;

import com.example.bank_app.dto.accountdto.AccountRequestDto;
import com.example.bank_app.dto.accountdto.AccountResponseDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.util.DtoCreator;
import com.example.bank_app.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test class for AccountMapper")
class AccountMapperTest {

    private final AccountMapper accountMapper = new AccountMapperImpl();


    @Test
    void shouldMapAccountToDtoResponse() {
        Account account = EntityCreator.getAccount1();
        AccountResponseDto dto = accountMapper.accountToDtoResponse(account);
        compareEntityWithDto(account, dto);

    }

    @Test
    void shouldMapDtoRequestToAccount() {
        AccountRequestDto dto = DtoCreator.getAccountRequestDto();
        dto.setCity("aaaa");
        Account account = accountMapper.dtoRequestToAccount(dto);
        assertEquals(account.getCity(), dto.getCity());
    }

    @Test
    void shouldMapListToListDto() {
        List<Account> accountList = new ArrayList<>();
        accountList.add(EntityCreator.getAccount1());
        List<AccountResponseDto> accountRequestDtoList = accountMapper.accountsToDto(accountList);
        compareAccountListWithListDto(accountList, accountRequestDtoList);
    }

    private void compareAccountListWithListDto(List<Account> accountList, List<AccountResponseDto> accountRequestDtoList) {
        assertEquals(accountList.size(), accountRequestDtoList.size());
        for (int i = 0; i < accountList.size(); i++) {
            compareEntityWithDto(accountList.get(i), accountRequestDtoList.get(i));
        }
    }

    private void compareEntityWithDto(Account account, AccountResponseDto dto) {
        assertAll(
                ()->assertEquals(account.getId().toString(),dto.getId()),
                () -> assertEquals(account.getEmail(), dto.getEmail()),
                () -> assertEquals(account.getFirstName(), dto.getFirstName()),
                () -> assertEquals(account.getLastName(), dto.getLastName()),
                () -> assertEquals(account.getCountry(), dto.getCountry()),
                () -> assertEquals(account.getCity(), dto.getCity()),
                () -> assertEquals(account.getAmountOfMoney(), dto.getAmountOfMoney())
        );
    }
}
