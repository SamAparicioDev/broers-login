package com.brears.login.configurations.jwt;

import com.brears.login.entities.UserEntity;
import com.brears.login.repositories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
@Configuration
public class CustomerUserDetailService implements UserDetailsService {
    @Autowired
    private UserEntityRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmailUser(username);
        return new User(userEntity.getEmailUser(),userEntity.getPasswordUser(), AuthorityUtils.NO_AUTHORITIES);
    }}
