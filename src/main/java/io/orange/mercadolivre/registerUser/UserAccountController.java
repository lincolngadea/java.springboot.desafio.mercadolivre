package io.orange.mercadolivre.registerUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/mercadolivre")
public class UserAccountController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UserAccountRepository listUsername;

    //Registration Block User
    @Transactional
    @PostMapping("/usuario")
    public ResponseEntity<?> saveUser(@RequestBody @Valid NewUserAccountRequest userRequest) {
        UserAccount user = userRequest.toModel();
        manager.persist(user);
        return ResponseEntity.ok(user.toString());
    }

    @GetMapping("/usuario/{user}")
    @Transactional
    public Optional<?> listUser(@PathVariable("user") @Valid String username) {
        UserAccount byUsername = listUsername.findByUsername(username).get();
        return Optional.ofNullable(byUsername);
    }
}
