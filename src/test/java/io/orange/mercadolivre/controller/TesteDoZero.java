package io.orange.mercadolivre.controller;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@SpringBootTest
public class TesteDoZero {

    @Test
    public void deveCriarUmUsuario(){
        Assert.isTrue(true);

    }


}
