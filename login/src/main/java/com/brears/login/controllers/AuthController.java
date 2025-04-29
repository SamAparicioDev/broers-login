package com.brears.login.controllers;

import com.brears.login.models.LoginRequest;
import com.brears.login.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(loginService.authenticate(loginRequest), HttpStatus.OK);
    }
}
