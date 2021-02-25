package io.orange.mercadolivre.controller;

import io.orange.mercadolivre.entity.User;
import io.orange.mercadolivre.request.NewUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/usuario")
    public List<NewUserRequest> listUsers(){
        return manager.createQuery("from User", User.class)
                .getResultStream()
                .map(NewUserRequest::new)
                .collect(Collectors.toList());
    }    //Block End


}
