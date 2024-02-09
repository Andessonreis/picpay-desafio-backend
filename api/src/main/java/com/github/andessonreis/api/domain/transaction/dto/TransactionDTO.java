package com.github.andessonreis.api.domain.transaction.dto;

import java.math.BigDecimal;

public record TransactionDTO(

    Long senderId,
    Long receiverId,
    BigDecimal amount
    
) {}
