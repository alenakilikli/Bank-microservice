package com.example.bank_app.entity.converter;

import com.example.bank_app.entity.enums.TransactionStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TransactionStatusConverter implements AttributeConverter<TransactionStatus, String> {
    @Override
    public String convertToDatabaseColumn(TransactionStatus transactionStatus) {
        if (transactionStatus == null) return null;
        return transactionStatus.getName().toUpperCase();
    }

    @Override
    public TransactionStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return TransactionStatus.findByName(dbData.toUpperCase());
    }
}
