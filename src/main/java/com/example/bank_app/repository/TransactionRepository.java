package com.example.bank_app.repository;

import com.example.bank_app.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface TransactionRepository extends JpaRepository<Transaction, String> {

    Optional<Transaction> findById(UUID id);

    List<Transaction> findAll();

    @Query("select t from Transaction t where t.accountFrom = :accountFrom")
    List<Transaction> findAllByAccountFrom(@Param("accountFrom") UUID accountFrom);

    @Query("select t from Transaction t where t.accountTo = :accountTo")
    List<Transaction> findAllByAccountTo(@Param("accountTo") UUID accountTo);


}
