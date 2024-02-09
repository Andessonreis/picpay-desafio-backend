package com.github.andessonreis.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.andessonreis.api.domain.user.User;


public interface UserRepository extends JpaRepository<User, Long> {}
