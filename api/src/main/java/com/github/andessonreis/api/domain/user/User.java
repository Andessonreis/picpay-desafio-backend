    package com.github.andessonreis.api.domain.user;

    import java.math.BigDecimal;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.Table;
    import lombok.Data;
    import lombok.EqualsAndHashCode;

    @Entity(name = "users")
    @Table(name = "users")
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

    }