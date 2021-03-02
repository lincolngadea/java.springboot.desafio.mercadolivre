package io.orange.mercadolivre.controller;

import io.orange.mercadolivre.model.UserLogin;
import io.orange.mercadolivre.model.request.NewUserLoginRequest;
import io.orange.mercadolivre.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/mercadolivre")
public class UserLoginController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UserLoginRepository listUsername;

    //Registration Block User
    @Transactional
    @PostMapping("/usuario")
    public ResponseEntity<UserLogin> saveUser(@RequestBody @Valid NewUserLoginRequest userRequest) {
        UserLogin user = userRequest.toModel();
        manager.persist(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/usuario/{user}")
    @Transactional
    public Optional<?> listUser(@PathVariable("user") @Valid String username) {
        Query byUsername = listUsername.findByUsername(manager, username);
        return Optional.ofNullable(byUsername.getSingleResult());
    }
}
