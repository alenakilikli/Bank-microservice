package com.example.bank_app.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TransactionType {

    WITHDRAW(2, "withdraw"),
    DEPOSIT(4, "deposit");

    private final Integer id;
    private final String name;

    @JsonCreator
    public static TransactionType findByName(String typeName) {
        if (typeName == null) {
            return null;
        }

        return Arrays.stream(TransactionType.values())
                .filter(x -> x.getName().equalsIgnoreCase(typeName))
                .findFirst()
                .orElse(null);
    }


}


