package com.example.bank_app.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TransactionType {

//    DIVIDEND,
//    WITHDRAW,
//    TRANSFER,
//    DEPOSIT,
//    ATM;


    DIVIDEND(1, "dividend"),
    WITHDRAW(2, "withdraw"),
    TRANSFER(3, "transfer"),
    DEPOSIT(4, "deposit"),
    ATM(5, "ATM");

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

class Main{
    public static void main(String[] args) {
        TransactionType type = TransactionType.findByName("sent");
        System.out.println(type);
    }
}
