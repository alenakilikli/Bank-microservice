package com.example.bank_app.entity;

import com.example.bank_app.entity.converter.TransactionStatusConverter;
import com.example.bank_app.entity.converter.TransactionTypeConverter;
import com.example.bank_app.entity.enums.TransactionStatus;
import com.example.bank_app.entity.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
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
   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Instant dateTime;

    @Column(name = "transactions_type")
    @Enumerated(EnumType.STRING)
    @Convert(converter = TransactionTypeConverter.class)
    private TransactionType type;

    @Column(name = "transaction_status")
    @Enumerated(EnumType.STRING)
    @Convert(converter = TransactionStatusConverter.class)
    private TransactionStatus status;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "account_from")
    private String accountFrom;

    @Column(name = "account_to")
    private String accountTo;


    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", type=" + type +
                ", status=" + status +
                ", amount=" + amount +
                ", accountFrom='" + accountFrom + '\'' +
                ", accountTo='" + accountTo + '\'' +
//                ", account=" + account +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(dateTime, that.dateTime) && type == that.type
                && status == that.status && Objects.equals(amount, that.amount) &&
                Objects.equals(accountFrom, that.accountFrom) && Objects.equals(accountTo, that.accountTo) ;
                //&& Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, type, status, amount, accountFrom, accountTo);
    }
}
