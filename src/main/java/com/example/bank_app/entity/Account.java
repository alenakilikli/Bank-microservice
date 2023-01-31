package com.example.bank_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "account")
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "email")
    private String email;
    @Column(name = "creation_date")
    private Instant creationDate;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "amounts")
    private BigDecimal amountOfMoney;
    @ManyToMany
    private List<Transaction> transactions;

}
