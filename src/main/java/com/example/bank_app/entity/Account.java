package com.example.bank_app.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "accounts")
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "com.example.bank_app.generator.UuidTimeSequenceGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "creation_date")
    @JsonSerialize(using = InstantSerializer.class)
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

    @ManyToMany(mappedBy = "accounts")
    private List<Transaction> transactions;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "email = " + email + ", " +
                "creationDate = " + creationDate + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "country = " + country + ", " +
                "city = " + city + ", " +
                "amountOfMoney = " + amountOfMoney + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Account account = (Account) o;
        return getId() != null && Objects.equals(getId(), account.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
