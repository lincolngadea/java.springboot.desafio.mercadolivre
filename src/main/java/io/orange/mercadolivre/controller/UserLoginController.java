package io.orange.mercadolivre.controller;

import io.orange.mercadolivre.entity.UserLogin;
import io.orange.mercadolivre.request.NewUserLoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mercadolivre")
public class UserLoginController {

    @PersistenceContext
    private EntityManager manager;

    //Registration Block User
    @Transactional
    @PostMapping("/usuario")
    public ResponseEntity<UserLogin> saveUser(@RequestBody @Valid NewUserLoginRequest userRequest){
        UserLogin user = userRequest.toModel();
        manager.persist(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/usuario/{user}")
    @Transactional
    public Optional<?> listUser(@PathVariable("user") @Valid String username){

        Query query = manager.createQuery("SELECT u FROM UserLogin u WHERE u.username =:user", UserLogin.class)
                .setParameter("user", username);
        return Optional.ofNullable(query.getSingleResult());
    }    //Block End


}
