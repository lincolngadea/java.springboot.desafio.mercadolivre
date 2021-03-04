package io.orange.mercadolivre.registerDetails;

import io.orange.mercadolivre.registerProduct.Product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "characteristic")
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @ManyToOne
    @JoinColumn(name = "idProduct", nullable = false)
    @NotNull
    private Product product;

    @Deprecated
    public Details() {
    }

    public Details(@NotBlank String title, @NotBlank String description) {
        this.title = title;
        this.description = description;

    }

    @Override
    public String toString() {
        return "Details{" +
                "id=" + id +
                ", name='" + title + '\'' +
                ", description='" + description + '\'' +
                ", product=" + product +
                '}';
    }
}
