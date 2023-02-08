package com.example.bank_app.entity;

import com.example.bank_app.entity.converter.TransactionTypeConverter;
import com.example.bank_app.entity.enums.TransactionType;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "com.example.bank_app.generator.UuidTimeSequenceGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "date_time")
    private Instant dateTime;

    @Column(name = "transactions_type")
    @Enumerated(EnumType.STRING)
    @Convert(converter = TransactionTypeConverter.class)
    private TransactionType type;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "account_from")
    private String accountFrom;

    @Column(name = "account_to")
    private String accountTo;

    @ManyToOne
    private Account account;


}
