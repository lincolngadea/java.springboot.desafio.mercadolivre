package io.orange.mercadolivre.registerProduct;

import io.orange.mercadolivre.registerDetails.RejectsRepeatedDetails;
import io.orange.mercadolivre.registerUser.UserAccount;
import io.orange.mercadolivre.registerUser.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/mercadolivre")
public class ProductController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new RejectsRepeatedDetails());
    }

    @PostMapping("/product")
    @Transactional
    public String createProduct(@RequestBody @Valid NewProductRequest request){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserAccount usernameAuth = userAccountRepository.findByUsername(auth.getName()).get();

        Product product = request.toModel(manager, usernameAuth);
        manager.persist(product);
        return request.toString();
    }
}
