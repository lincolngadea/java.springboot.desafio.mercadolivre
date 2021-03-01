package io.orange.mercadolivre.service.impl;

import io.orange.mercadolivre.controller.UserLoginController;
import io.orange.mercadolivre.entity.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;

@Service
public class UserLoginServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserLoginController userLoginController;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserLogin userLogin = (UserLogin) userLoginController.listUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return User
                .builder()
                .username(userLogin.getUsername())
                .password(userLogin.getPassword())
                .roles("USER")
                .build();
    }
}
