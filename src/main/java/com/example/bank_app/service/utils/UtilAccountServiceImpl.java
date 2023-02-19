package com.example.bank_app.service.utils;

import com.example.bank_app.entity.Account;
import com.example.bank_app.entity.Transaction;
import com.example.bank_app.entity.enums.TransactionStatus;
import com.example.bank_app.entity.enums.TransactionType;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
public class UtilAccountServiceImpl {

    public static void decreaseBalance(BigDecimal amount, Account account) {
        BigDecimal fromAccountBalance = account.getAmountOfMoney().subtract(amount);
        account.setAmountOfMoney(fromAccountBalance);
    }

    public static void increaseBalance(BigDecimal amount, Account account) {
        BigDecimal fromAccountBalance = account.getAmountOfMoney().add(amount);
        account.setAmountOfMoney(fromAccountBalance);
    }

    public static void setStatusApproved(Transaction fromTransaction) {
        fromTransaction.setStatus(TransactionStatus.APPROVED);
    }

    public static void setStatusDenied(Transaction fromTransaction) {
        fromTransaction.setStatus(TransactionStatus.DENIED);
    }

    public static void addTransactionToAccount(Account fromAccount, Transaction fromAccountTransaction) {
        fromAccount.getTransactions().add(fromAccountTransaction);
    }


    public static Transaction create(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {
        return Transaction.builder()
                .dateTime(Instant.now())
                .type(TransactionType.BALANCE)
                .status(TransactionStatus.PENDING)
                .amount(amount)
                .accountFrom(String.valueOf(fromAccountId))
                .accountTo(String.valueOf(toAccountId))
                .build();
    }

    public static boolean checkBalance(BigDecimal amount) {
        return amount.compareTo(new BigDecimal(0)) > 0;

    }

}
