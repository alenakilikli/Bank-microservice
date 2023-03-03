package com.example.bank_app.repository;

import com.example.bank_app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, String> {

    List<Account> findAll();

    Optional<Object> findById(UUID id);

    List<Account> findAllByCreationDate(Instant creationDate);

    Account findAccountById(UUID id);

    List<Account> findAllByCityIgnoreCase(String city);
}
