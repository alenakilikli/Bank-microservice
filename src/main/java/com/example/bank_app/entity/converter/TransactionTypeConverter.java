package com.example.bank_app.entity.converter;

import com.example.bank_app.entity.type.TransactionType;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TransactionTypeConverter implements AttributeConverter<TransactionType, String> {
    @Override
    public String convertToDatabaseColumn(TransactionType transactionType) {
        if (transactionType == null) {
            return null;
        }
        return transactionType.getName();
    }

    @SneakyThrows
    @Override
    public TransactionType convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        return Stream.of(TransactionType.values())
                .filter(c->c.getName().equals(s))
                .findFirst()
                .orElseThrow(IllegalAccessException::new);
    }
}
