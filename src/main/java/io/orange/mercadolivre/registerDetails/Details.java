package io.orange.mercadolivre.registerDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    @Deprecated
    public Details() {
    }

    public Details(@NotBlank String title, @NotBlank String description) {
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
        return "Details{" +
                "id=" + id +
                ", name='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
