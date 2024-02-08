package com.github.andessonreis.api.domain.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.github.andessonreis.api.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "transactions")
@Table(name = "transactions")
@Data
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private BigDecimal amout;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payer;

    @ManyToOne
    @JoinColumn(name = "payee_id")
    private User payee;

    private LocalDateTime transactionTime;
}
