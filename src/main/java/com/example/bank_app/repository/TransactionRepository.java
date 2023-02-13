package com.example.bank_app.repository;

import com.example.bank_app.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository extends JpaRepository<Transaction,Long> {


}
