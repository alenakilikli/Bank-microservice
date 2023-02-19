package com.example.bank_app.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TransactionStatus {
    APPROVED(1,"approved"),
    DENIED(2,"denied"),
    PENDING(3,"pending");

    private final Integer id;
    private final String name;

    @JsonCreator
    public static TransactionStatus findByName(String statusName) {
        if (statusName == null) {
            return null;
        }

        return Arrays.stream(TransactionStatus.values())
                .filter(x -> x.getName().equalsIgnoreCase(statusName))
                .findFirst()
                .orElse(null);
    }
}
