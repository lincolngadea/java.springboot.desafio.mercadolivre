package io.orange.mercadolivre.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.orange.mercadolivre.model.request.NewUserLoginRequest;
import io.orange.mercadolivre.util.MockBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@SpringBootTest
class UserLoginTest {

    @PersistenceContext
    private EntityManager manager;

    static String ML_API = "/mercadolivre/usuario";

    @Autowired
    MockMvc mvc;

    @Test
    @Transactional
    @DisplayName("Must create a user successfully")
    public void returnUserCreate() throws Exception {

        String json = new ObjectMapper().writeValueAsString(new NewUserLoginRequest("teste2@logado.com", "123456"));

        mvc
                .perform(MockBuilder.run(ML_API, json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").value("teste2@logado.com"));
//               .andExpect(jsonPath("$[0].status").isNumber());
    }

    @Test
    @DisplayName("Must return 400 error for duplicate email")
    @Transactional
    public void noDuplicateEmail() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new NewUserLoginRequest("teste@logado.com", "123456"));

        mvc
                .perform(MockBuilder.run(ML_API, json))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("Must return user by email ")
    @Test
    @Transactional
    public void returnUserByEmail() throws Exception {
        mvc

                .perform(get("/mercadolivre/usuario/teste@logado.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}