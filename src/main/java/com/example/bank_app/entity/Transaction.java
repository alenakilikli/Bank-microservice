package com.example.bank_app.entity;

import com.example.bank_app.entity.converter.TransactionStatusConverter;
import com.example.bank_app.entity.converter.TransactionTypeConverter;
import com.example.bank_app.entity.enums.TransactionStatus;
import com.example.bank_app.entity.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions", schema = "public")
public class Transaction {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "com.example.bank_app.generator.UuidTimeSequenceGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "date_time")
    @JsonSerialize(using = InstantSerializer.class)
    private Instant dateTime;

    @Column(name = "transactions_type")
    @Enumerated(EnumType.STRING)
    @Convert(converter = TransactionTypeConverter.class)
    private TransactionType type;

    @Column(name = "transactions_status")
    @Enumerated(EnumType.STRING)
    @Convert(converter = TransactionStatusConverter.class)
    private TransactionStatus status;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "account_from")
    private String accountFrom;

    @Column(name = "account_to")
    private String accountTo;

    @ManyToMany
    @JoinTable(name = "transactions_accounts",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "accounts_id"))
    private List<Account> accounts = new ArrayList<>();

}
