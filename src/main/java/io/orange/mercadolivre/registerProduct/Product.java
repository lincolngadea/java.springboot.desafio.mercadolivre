package io.orange.mercadolivre.registerProduct;

import io.orange.mercadolivre.registerCategory.Category;
import io.orange.mercadolivre.registerDetails.NewDetailsRequest;
import io.orange.mercadolivre.registerUser.UserAccount;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String name;
    private @NotNull @Positive BigDecimal price;
    private @NotNull @Min(0) Integer availableQuantity;
    private @NotBlank @Length(max = 1000) String description;
    private @ManyToOne @NotNull @Valid Category category;
    private @NotNull @Valid @ManyToOne UserAccount usernameAuth;

    private LocalDateTime instantDate = LocalDateTime.now();

    @OneToMany(mappedBy ="product", cascade = CascadeType.PERSIST)
    private Set<DetailProduct> details = new HashSet<>();

    @Deprecated
    public Product() {
    }

    public Product(
            @NotBlank String name,
            @NotNull @DecimalMin(value = "1.0", inclusive = false)  @Positive BigDecimal price,
            @NotNull @Min(0) Integer availableQuantity,
            @NotNull String description,
            @NotNull @Valid Category category,
            @NotBlank UserAccount usernameAuth,
            Collection<NewDetailsRequest> details) {
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.description = description;
        this.category = category;
        this.usernameAuth = usernameAuth;
        this.details.addAll(details
                .stream().map(detail -> detail.toModel(this))
                .collect(Collectors.toSet()));

        Assert.isTrue(this.details.size()>=3,"All product must have minimous of 3 characters!");
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<DetailProduct> getDetails() {
        return details;
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

    public UserAccount getUsernameAuth() {
        return usernameAuth;
    }

    public Category getCategory() {return category; }

    public LocalDateTime getInstantDate() {
        return instantDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "description='" + description + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
