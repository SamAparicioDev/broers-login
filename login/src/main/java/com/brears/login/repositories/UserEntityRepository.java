package com.brears.login.repositories;

import com.brears.login.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmailUser(String emailUser);
    UserEntity findByToken(UUID token);
}
