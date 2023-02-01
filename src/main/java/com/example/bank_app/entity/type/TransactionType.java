package com.example.bank_app.entity.type;

import com.example.bank_app.utils.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

public enum TransactionType {

    DIVIDEND,
            //(1, "dividend"),
    WITHDRAW,
    //(2, "withdraw"),
    TRANSFER,
    //(3, "transfer"),
    DEPOSIT,
    //(4, "deposit"),
    ATM;
    //(5, "ATM");
//
//    private int id;
//    private String name;

//    @JsonCreator
//    public static TransactionType findByName(String typeName) {
//        if (typeName == null) {
//            return TransactionType.valueOf(ValidationMessages.MISS_TYPE_NAME);
//        }
//
//        return Arrays.stream(TransactionType.values())
//                .filter(x -> x.getName().equals(typeName))
//                .findFirst()
//                .orElse(TransactionType.valueOf(ValidationMessages.NOT_FOUND_TYPE_NAME));
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    @JsonValue
//    public String getName() {
//        return name;
//    }


    }
