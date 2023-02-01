package com.example.bank_app.mapTest;

import com.example.bank_app.dto.accountDto.AccountRequestDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.mapper.AccountMapper;
import com.example.bank_app.mapper.AccountMapperImpl;
import com.example.bank_app.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test class for AccountMapper")
public class AccountMapperTest {

    private final AccountMapper accountMapper = new AccountMapperImpl();


    @Test
    public void shouldMapAccountToDto() {
        Account account = EntityCreator.getAccount();
        AccountRequestDto dto = accountMapper.accountToDto(account);
        compareEntityWithDto(account, dto);

    }

    @Test
    public void shouldMapDtoToAccount() {
        AccountRequestDto dto = new AccountRequestDto();
        dto.setCity("aaaa");
        Account account = accountMapper.dtoToAccount(dto);
        assertEquals(account.getCity(), dto.getCity());
    }

    @Test
    public void shouldMapListToListDto() {
        List<Account> accountList = new ArrayList<>();
        accountList.add(EntityCreator.getAccount());
        List<AccountRequestDto> accountRequestDtoList = accountMapper.accountsToDto(accountList);
        compareAccountListWithListDto(accountList, accountRequestDtoList);
    }

    private void compareAccountListWithListDto(List<Account> accountList, List<AccountRequestDto> accountRequestDtoList) {
        assertEquals(accountList.size(), accountRequestDtoList.size());
        for (int i = 0; i < accountList.size(); i++) {
            compareEntityWithDto(accountList.get(i), accountRequestDtoList.get(i));
        }
    }

    private void compareEntityWithDto(Account account, AccountRequestDto dto) {
        assertAll(
                () -> assertEquals(account.getEmail(), dto.getEmail()),
                () -> assertEquals(account.getCreationDate(), dto.getCreationDate()),
                () -> assertEquals(account.getFirstName(), dto.getFirstName()),
                () -> assertEquals(account.getLastName(), dto.getLastName()),
                () -> assertEquals(account.getCountry(), dto.getCountry()),
                () -> assertEquals(account.getCity(), dto.getCity())
        );
    }
}
