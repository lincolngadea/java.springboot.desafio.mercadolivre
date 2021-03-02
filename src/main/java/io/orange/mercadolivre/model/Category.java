package io.orange.mercadolivre.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @ManyToOne
    private Category categoryMother;

    @Deprecated
    public Category() {
    }

    public Category(@NotBlank String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMother(Category categoryMother) {
        this.categoryMother = categoryMother;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryMother=" + categoryMother +
                '}';
    }


}
