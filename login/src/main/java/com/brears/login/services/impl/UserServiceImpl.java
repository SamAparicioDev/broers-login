package com.brears.login.services.impl;

import com.brears.login.entities.UserEntity;
import com.brears.login.models.UserDTO;
import com.brears.login.models.UserDTOState;
import com.brears.login.models.UserPasswordDTO;
import com.brears.login.repositories.UserEntityRepository;
import com.brears.login.services.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserEntityService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserDTO> registerUser(UserDTO user) {
        userEntityRepository.save(new UserEntity(user.nameUser(),user.lastNameUser(),user.email()));
        return Optional.of(user);
    }

    @Override
    public Optional<UserEntity> findyById(Long idUser) {
       return userEntityRepository.findById(idUser);
    }

    @Override
    public List<UserDTOState> findAllUsers() {
        List<UserDTOState> userDTOS = new ArrayList<>();
         userEntityRepository.findAll().forEach((e)->{
            userDTOS.add(new UserDTOState(e.getUserId(),e.getNameUser(),e.getLastNameUser(),e.getEmailUser(),e.getIsActive()));
        });
         return userDTOS;
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userEntityRepository.findByEmailUser(email);
    }

    @Override
    public Boolean createPassword(UUID token, UserPasswordDTO password) {
        UserEntity userEntity = userEntityRepository.findByToken(token);
        if(userEntity!=null) {
            userEntity.setPasswordUser(passwordEncoder.encode(password.password()));
            userEntity.setToken(null);
            userEntityRepository.save(userEntity);
            return true;
        }

        return false;
    }

    @Override
    public Boolean modifyUserExists(Long idUser,Boolean modified) {
       UserEntity userEntity = userEntityRepository.findById(idUser).orElse(null);
        assert userEntity != null;
        userEntity.setIsActive(modified);
        userEntityRepository.save(userEntity);
        return userEntity.getIsActive();
    }

    @Override
    public UUID generateToken(Long idUser) {
        UserEntity userEntity = userEntityRepository.findById(idUser).orElse(null);
        assert userEntity != null;
        userEntity.setToken(UUID.randomUUID());
        userEntityRepository.save(userEntity);
        return null;
    }

    @Override
    public UserEntity findByToken(UUID token) {
        return userEntityRepository.findByToken(token);
    }


}
