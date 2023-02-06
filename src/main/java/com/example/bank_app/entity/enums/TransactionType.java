package com.example.bank_app.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionType {

    DIVIDEND,
    WITHDRAW,
    TRANSFER,
    DEPOSIT,
    ATM;


//    DIVIDEND(1, "dividend"),
//    WITHDRAW(2, "withdraw"),
//    TRANSFER(3, "transfer"),
//    DEPOSIT(4, "deposit"),
//    ATM(5, "ATM");
//
//    private final int id;
//    private final String name;
//
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
//

}
