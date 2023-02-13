package com.example.bank_app.repository;

import com.example.bank_app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Override
    List<Account> findAll();

    Optional<Object> findById(UUID id);

    List<Account> findAllByCreationDate(String date);

    List<Account> findAllByCityIgnoreCaseOrderByCreationDate(String city);


}
