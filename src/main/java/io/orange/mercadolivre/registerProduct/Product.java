package io.orange.mercadolivre.registerProduct;

import io.orange.mercadolivre.registerCategory.Category;
import io.orange.mercadolivre.registerDetails.Details;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    @DecimalMin(value = "1.0", inclusive = false)
    @Positive
    private BigDecimal price;
    @NotNull
    @Min(0)
    private Integer availableQuantity;
    @NotNull
    @Column(length = 1000)
    private String description;
    @NotNull
    private Long idCategoy;
    private LocalDateTime instantDate = LocalDateTime.now();

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private Set<Details> details;

    @ManyToOne
    private Category category;

    @Deprecated
    public Product() {
    }

    public Product(
            @NotBlank String name,
            @NotNull @DecimalMin(value = "1.0", inclusive = false) BigDecimal price,
            @NotNull @Min(0) Integer availableQuantity,
            @NotNull String description,
            @NotNull Long idCategoy) {
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.description = description;
        this.idCategoy = idCategoy;
    }


    public Long getId() {
        return id;
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

    public Long getIdCategoy() {
        return idCategoy;
    }

    public LocalDateTime getInstantDate() {
        return instantDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", availableQuantity=" + availableQuantity +
                ", description='" + description + '\'' +
                ", idCategoy=" + idCategoy +
                ", instantDate=" + instantDate +
                '}';
    }
}
