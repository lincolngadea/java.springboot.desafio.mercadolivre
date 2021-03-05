package io.orange.mercadolivre.registerDetails;

import io.orange.mercadolivre.registerProduct.DetailProduct;
import io.orange.mercadolivre.registerProduct.Product;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NewDetailsRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;

    @Deprecated
    public NewDetailsRequest() {
    }

    public NewDetailsRequest(@NotBlank String title, @NotBlank String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "NewDetailsRequest{" +
                "description='" + description + '\'' +
                '}';
    }

    public DetailProduct toModel(@NotNull @Valid Product product) {
        return  new DetailProduct(title,description,product);
    }
}
