package com.brears.login.services;

import com.brears.login.models.LoginRequest;

public interface LoginService {
    public String authenticate(LoginRequest loginRequest);
}
