package com.github.andessonreis.api.domain.user.dto;

import java.math.BigDecimal;

import com.github.andessonreis.api.domain.user.UserType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO(

    @NotNull
    String name,
    @NotNull
    String document,
    @NotNull @Email
    String email,
    @NotNull @Size(min = 4, max = 20)
    String password,
    @NotNull
    UserType userType,
    @NotNull 
    BigDecimal balance

) {}