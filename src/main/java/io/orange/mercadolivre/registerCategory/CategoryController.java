package io.orange.mercadolivre.registerCategory;

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
public class CategoryController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/categories")
    @Transactional
    public ResponseEntity<Category> createCategory(@RequestBody @Valid NewCategoryRequest request){
        Category category = request.toModel(manager);
        manager.persist(category);
        return ResponseEntity.ok(category);
    }
}
