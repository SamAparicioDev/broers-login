package com.brears.login.services;

import com.brears.login.entities.UserEntity;
import com.brears.login.models.UserDTO;
import com.brears.login.models.UserDTOState;
import com.brears.login.models.UserPasswordDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface UserEntityService {
     Optional<UserDTO> registerUser(UserDTO user);
     Optional<UserEntity> findyById(Long idUser);
     List<UserDTOState> findAllUsers();
     UserEntity findByEmail(String email);
     Boolean createPassword(UUID token, UserPasswordDTO password);
     Boolean modifyUserExists(Long idUser,Boolean modified);
     UUID generateToken(Long idUser);
     UserEntity findByToken(UUID token);
}
