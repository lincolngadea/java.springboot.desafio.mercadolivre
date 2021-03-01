package io.orange.mercadolivre.service.impl;

import io.orange.mercadolivre.entity.UserLogin;
import io.orange.mercadolivre.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
public class UserLoginServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserLoginRepository userLoginRepository;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Query byUsername = userLoginRepository.findByUsername(manager, username);
        UserLogin userLogin = (UserLogin) byUsername.getSingleResult();
        return User
                .builder()
                .username(userLogin.getUsername())
                .password(userLogin.getPassword())
                .roles("USER")
                .build();
    }
}
