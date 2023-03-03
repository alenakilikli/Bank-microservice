package com.example.bank_app.enums;

import com.example.bank_app.entity.converter.TransactionTypeConverter;
import com.example.bank_app.entity.enums.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TransactionTypeConverterTest {
    private static final TransactionTypeConverter CONVERTER = new TransactionTypeConverter();


    @Test
    @DisplayName("should convert to string")
    public void shouldConvertToStringWhenValueStatusWithdraw() {
        TransactionType givenType = TransactionType.WITHDRAW;
        String expected = givenType.getName().toUpperCase();
        var actual = CONVERTER.convertToDatabaseColumn(givenType);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return a type withdraw when string = withdraw")
    public void shouldReturnTypeWithdrawWhenStringIsWithdraw() {
        TransactionType expected = TransactionType.WITHDRAW;
        String typeActual = "withdraw";
        Assertions.assertEquals(expected, TransactionType.findByName(typeActual));
    }

    @Test
    @DisplayName("should return a type deposit when string = deposit")
    public void shouldReturnTypeDepositWhenStringIsDeposit() {
        TransactionType expected = TransactionType.DEPOSIT;
        String typeActual = "deposit";
        Assertions.assertEquals(expected, TransactionType.findByName(typeActual));

    }

}
