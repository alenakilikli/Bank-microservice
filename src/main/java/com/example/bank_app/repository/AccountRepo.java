package com.example.bank_app.repository;

import com.example.bank_app.entity.Account;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.awt.print.Pageable;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepo extends JpaRepository<Account,Long> {

    List<Account> findAllByCreationDateOrCityIgnoreCaseOrderByCreationDateDesc(Instant date, String city);


    @Override
    List<Account> findAll();

    Optional<Object> findById(UUID id);
}
