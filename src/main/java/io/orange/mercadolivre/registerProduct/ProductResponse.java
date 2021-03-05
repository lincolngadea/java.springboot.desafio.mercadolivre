package io.orange.mercadolivre.registerProduct;

import io.orange.mercadolivre.registerCategory.Category;
import io.orange.mercadolivre.registerUser.UserAccount;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class ProductResponse {

    private String name;
    private BigDecimal price;
    private Integer availableQuantity;
    private String description;
    private Category category;
    private UserAccount usernameAuth;
    private LocalDateTime localDateTime;
    private Set<DetailProduct> details;

    public ProductResponse(Product product){
        name = product.getName();
        price = product.getPrice();
        availableQuantity = product.getAvailableQuantity();
        description = product.getDescription();
        category = product.getCategory();
        usernameAuth = product.getUsernameAuth();
        localDateTime = product.getInstantDate();
        details = product.getDetails();
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

    public Category getCategory() {
        return category;
    }

    public UserAccount getUsernameAuth() {
        return usernameAuth;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Set<DetailProduct> getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", availableQuantity=" + availableQuantity +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", usernameAuth=" + usernameAuth +
                ", localDateTime=" + localDateTime +
                ", details=" + details +
                '}';
    }
}
