package com.example.bank_app.repository;

import com.example.bank_app.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface TransactionRepository extends JpaRepository<Transaction,String> {

    Optional<Transaction> findById(UUID id);
    List<Transaction> findAll();


}
