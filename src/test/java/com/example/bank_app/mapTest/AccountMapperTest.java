package com.example.bank_app.mapTest;

import com.example.bank_app.dto.accountDto.AccountRequestDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.mapper.AccountMapper;
import com.example.bank_app.util.EntityCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
@DisplayName("Test class for AccountMapper")
public class AccountMapperTest {

    private final AccountMapper mapper ;

    public AccountMapperTest(AccountMapper mapper) {
        this.mapper = mapper;
    }

    @Test
    public void shouldMapAccountToDto() {
        Account account = EntityCreator.getAccount();
        AccountRequestDto dto = mapper.accountToDto(account);
        compareEntytyWithDto(account,dto);


    }
    @Test
    public void shouldMapDtoToAccount(){
//        AccountRequestDto dto = new AccountRequestDto();
//        dto.setCity("aaaa");
//        Account account = mapper.dtoToAccount(dto);
//        assertEquals(account.getCity(),dto.getCity());
        }

    private void compareEntytyWithDto(Account account,AccountRequestDto dto){
        assertAll(
                ()->assertEquals(account.getEmail(),dto.getEmail()),
                ()->assertEquals(account.getCreationDate(),dto.getCreationDate()),
                ()->assertEquals(account.getFirstName(),dto.getFirstName()),
                ()->assertEquals(account.getLastName(),dto.getLastName()),
                ()->assertEquals(account.getCountry(),dto.getCountry()),
                ()->assertEquals(account.getCity(),dto.getCity())
        );
    }
}
