package io.orange.mercadolivre.controller;

import io.orange.mercadolivre.entity.User;
import io.orange.mercadolivre.request.NewUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/mercadolivre")
public class UserController {

    @PersistenceContext
    private EntityManager manager;

    //Registration Block User
    @Transactional
    @PostMapping("/usuario")
    public ResponseEntity<User> saveUser(@RequestBody @Valid NewUserRequest userRequest){
        User user = userRequest.toModel();
        manager.persist(user);
        return ResponseEntity.ok(user);
    }
    //Block End


}
