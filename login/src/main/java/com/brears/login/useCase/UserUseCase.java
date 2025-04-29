package com.brears.login.useCase;

import com.brears.login.entities.UserEntity;
import com.brears.login.models.EmailDTO;
import com.brears.login.models.UserDTO;
import com.brears.login.services.EmailService;
import com.brears.login.services.UserEntityService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserUseCase {

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private EmailService emailService;

    @Transactional
    public Optional<UserDTO> registerUser(UserDTO userDTO) {
        String subject = "Email Send To Register User With Password: " + userDTO.nameUser() + " " + userDTO.lastNameUser();
        userEntityService.registerUser(userDTO);
        UserEntity userEntity = userEntityService.findByEmail(userDTO.email());
        userEntityService.generateToken(userEntity.getUserId());
        emailService.sendEmail(new EmailDTO(userDTO.email(),subject,String.valueOf(userEntity.getToken())));
        return Optional.of(userDTO);
    }

    public void lostPassword(Long id) {
        UserEntity userEntity = userEntityService.findyById(id).orElse(null);
        assert userEntity != null;
        userEntityService.generateToken(userEntity.getUserId());
        emailService.sendEmail(new EmailDTO(userEntity.getEmailUser(),"Lost password",String.valueOf(userEntity.getToken())));
    }
}
