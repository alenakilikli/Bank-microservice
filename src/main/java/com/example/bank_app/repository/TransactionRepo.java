package com.example.bank_app.repository;

import com.example.bank_app.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction,Long> {

    List<Transaction> findAllByAccountTo(String accountTo);


}
