package io.orange.mercadolivre.registerDetails;

import io.orange.mercadolivre.registerProduct.Product;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewDetailsRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @ManyToOne
    @JoinColumn(name = "idProduct",nullable = false)
    @NotNull
    private Product product;

    @Deprecated
    public NewDetailsRequest() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Product getProduct() {
        return product;
    }

    public Details toModel(){
        return new Details(this.title, this.description);
    }
}
