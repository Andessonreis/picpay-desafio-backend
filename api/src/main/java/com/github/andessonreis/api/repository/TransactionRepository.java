package com.github.andessonreis.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.andessonreis.api.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{}
