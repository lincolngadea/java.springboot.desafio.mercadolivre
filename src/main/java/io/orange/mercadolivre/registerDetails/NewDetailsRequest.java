package io.orange.mercadolivre.registerDetails;

import javax.validation.constraints.NotBlank;

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

    public Details toModel(){
        return new Details(this.title, this.description);
    }

    @Override
    public String toString() {
        return "NewDetailsRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
