package com.example.bank_app.entity;

import com.example.bank_app.entity.type.TransactionType;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "date_time")
    private Instant dateTime;
    @Column(name = "transactions_type")
    private TransactionType type;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "account_from")
    private String accountFrom;
    @Column(name = "account_to")
    private String accountTo;
    @ManyToMany
    @JoinColumn(name = "id")
    private List<Account> account;


}
