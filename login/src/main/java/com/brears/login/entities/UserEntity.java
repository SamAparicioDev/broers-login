package com.brears.login.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String nameUser;

    @Column(nullable = false)
    private String lastNameUser;

    @Column(nullable = false, unique = true)
    private String emailUser;

    @Column
    private String passwordUser;

    @Column
    private Boolean isActive = true;

    @Column
    private UUID token;

    public UserEntity(String nameUser, String lastNameUser, String emailUser) {
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.lastNameUser = lastNameUser;
    }
}
