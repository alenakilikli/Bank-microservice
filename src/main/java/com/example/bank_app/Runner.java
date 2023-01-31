package com.example.bank_app;
//
//import com.example.bank_app.entity.Transaction;
//import com.example.bank_app.repository.AccountRepo;
//import com.example.bank_app.repository.TransactionsRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.math.BigInteger;
//import java.time.Instant;
//import java.util.List;
//
//@Component
//public class Runner implements CommandLineRunner {
//
//    @Autowired
//    private AccountRepo accountRepo;
//    @Autowired
//    private TransactionsRepo transactionsRepo;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Transaction transaction1 = Transaction.builder()
//                .dateTime(Instant.now())
//                .type("send money to bank")
//                .amount(10000l)
//                .accountFrom("2")
//                .accountTo("5")
//                .build();
//        Transaction transaction2 = Transaction.builder()
//                .dateTime(Instant.now())
//                .type("send money to friend")
//                .amount(10000l)
//                .accountFrom("5")
//                .accountTo("15")
//                .build();
//        Transaction transaction3 = Transaction.builder()
//                .dateTime(Instant.now())
//                .type("send money to shop")
//                .amount(10000l)
//                .accountFrom("12")
//                .accountTo("555")
//                .build();
//
//        transactionsRepo.saveAll(List.of(transaction1,transaction2,transaction3));
//        System.out.println(transactionsRepo.findAllByAccountTo("5"));
//    }
//}
