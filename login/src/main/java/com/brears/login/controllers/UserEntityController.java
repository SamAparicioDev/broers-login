package com.brears.login.controllers;

import com.brears.login.entities.UserEntity;
import com.brears.login.models.UserDTO;
import com.brears.login.models.UserDTOState;
import com.brears.login.models.UserPasswordDTO;
import com.brears.login.services.UserEntityService;
import com.brears.login.useCase.UserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserEntityController {
    @Autowired
    private UserUseCase userUseCase;

    @Autowired
    private UserEntityService userEntityService;

    @PostMapping("/save")
    public ResponseEntity<Optional<UserDTO>> saveUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userUseCase.registerUser(userDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTOState>> findAllUsers() {
        return new ResponseEntity<>(userEntityService.findAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/{id}/{exists}")
    public ResponseEntity<Boolean> updateUser(@PathVariable Long id, @PathVariable boolean exists) {
        return new ResponseEntity<>(userEntityService.modifyUserExists(id, exists), HttpStatus.OK);
    }

    @PutMapping("/create-password/{token}")
    public ResponseEntity<Boolean> createPassword(@PathVariable UUID token, @RequestBody UserPasswordDTO password) {
        return new ResponseEntity<>(userEntityService.createPassword(token,password),HttpStatus.OK);
    }
    @GetMapping("/view-password/{id}")
    public ResponseEntity<String> viewPassword(@PathVariable Long id) {
        userUseCase.lostPassword(id);
        return new ResponseEntity<>("Mail with token to change password send", HttpStatus.OK);
    }
}
