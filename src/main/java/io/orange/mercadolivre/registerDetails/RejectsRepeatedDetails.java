package io.orange.mercadolivre.registerDetails;

import io.orange.mercadolivre.registerProduct.NewProductRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class RejectsRepeatedDetails implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return NewProductRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        NewProductRequest request = (NewProductRequest) target;
        Set<String> namesEquals = request.searchEqualDetails();
        if(!namesEquals.isEmpty()){
            errors.rejectValue("details",null,"Equal details are not allowed");
        }
    }
}
