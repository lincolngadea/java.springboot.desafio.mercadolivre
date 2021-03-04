package io.orange.mercadolivre.registerProduct;

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
public class ProductController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/product")
    @Transactional
    public ResponseEntity<Product> createProduct(@RequestBody @Valid NewProductRequest request){

        Product product = request.toModel();
        manager.persist(product);
        return ResponseEntity.ok(product);

    }
}
