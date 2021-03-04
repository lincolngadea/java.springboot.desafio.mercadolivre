package io.orange.mercadolivre.registerProduct;

import io.orange.mercadolivre.registerCategory.Category;
import io.orange.mercadolivre.config.validators.annotations.ExistsId;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.math.BigDecimal;

public class NewProductRequest {

    @NotBlank
    private String name;
    @NotNull
    @DecimalMin(value = "1.0", inclusive = false)
    @Positive
    private BigDecimal price;
    @NotNull
    @Min(0)
    private Integer availableQuantity;
    @NotBlank
    @Column(length = 1000)
    private String description;
    @NotNull
    @ExistsId(domainClass = Category.class, fieldName = "id")
    private Long idCategory;

    @Deprecated
    public NewProductRequest() {
    }

    public NewProductRequest(
            @NotBlank String name,
            @NotNull @DecimalMin(value = "1.0", inclusive = false)
            @Positive BigDecimal price,
            @NotNull @Min(0) Integer availableQuantity,
            @NotBlank String description,
            @NotNull Long idCategory) {
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.description = description;
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public String getDescription() {
        return description;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public Product toModel() {
        return new Product(this.name,this.price,this.availableQuantity,this.description,this.idCategory);
    }

    @Override
    public String toString() {
        return "NewProductRequest{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", availableQuantity=" + availableQuantity +
                ", description='" + description + '\'' +
                ", idCategory=" + idCategory +
                '}';
    }
}
