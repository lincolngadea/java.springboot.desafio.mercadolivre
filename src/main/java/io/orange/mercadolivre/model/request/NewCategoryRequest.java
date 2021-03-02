package io.orange.mercadolivre.model.request;

import io.jsonwebtoken.lang.Assert;
import io.orange.mercadolivre.config.validators.annotations.UniqueValue;
import io.orange.mercadolivre.model.Category;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class NewCategoryRequest {

    @NotBlank
    @UniqueValue(fieldName = "name",domainClass = Category.class)
    private String name;
    @Positive
    private Long idCategoryMother;

    public void setName(String name) {
        this.name = name;
    }

    public void setIdCategoryMother(Long idCategoryMother) {
        this.idCategoryMother = idCategoryMother;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategoryMother = idCategory;
    }

    @Override
    public String toString() {
        return "NewCategoryRequest{" +
                "name='" + name + '\'' +
                ", idCategoryMother=" + idCategoryMother +
                '}';
    }

    public Category toModel(EntityManager manager) {
        Category category = new Category(name);
        if(idCategoryMother!=null){
            Category categoryMother = manager.find(Category.class,idCategoryMother);
            Assert.notNull(categoryMother, "The id category must be valid!");
            category.setMother(categoryMother);
        }
        return category;
    }
}
