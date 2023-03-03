package com.example.bank_app.enums;

import com.example.bank_app.entity.converter.TransactionStatusConverter;
import com.example.bank_app.entity.enums.TransactionStatus;
import com.example.bank_app.entity.enums.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TransactionStatusConverterTest {
    private static final TransactionStatusConverter CONVERTER = new TransactionStatusConverter();

    @Test
    @DisplayName("should convert to string")
    void shouldConvertToStringWhenValueStatusPending() {
        TransactionStatus givenStatus = TransactionStatus.PENDING;
        String expected = givenStatus.getName().toUpperCase();
        var actual = CONVERTER.convertToDatabaseColumn(givenStatus);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return a status approved when string = approved")
    void shouldReturnStatusApprovedWhenStringIsApproved() {
        TransactionStatus expected = TransactionStatus.APPROVED;
        String statusActual = "approved";
        Assertions.assertEquals(expected, TransactionStatus.findByName(statusActual));
    }

    @Test
    @DisplayName("should return a status denied when string = denied")
    public void shouldReturnStatusDeniedWhenStringIsDenied() {
        TransactionStatus expected = TransactionStatus.DENIED;
        String statusActual = "denied";
        Assertions.assertEquals(expected, TransactionStatus.findByName(statusActual));

    }
}
