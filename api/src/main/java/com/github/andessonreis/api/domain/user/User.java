package com.github.andessonreis.api.domain.user;

import java.math.BigDecimal;

import com.github.andessonreis.api.domain.user.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;

    private UserType userType;
    
    private BigDecimal balance;

    public User(UserDTO userDTO) {

        this.name = userDTO.name();
        this.document = userDTO.document();
        this.email = userDTO.email();
        this.password  = userDTO.password();
        this.userType = userDTO.userType();
        this.balance = userDTO.balance();
        
    }
}
