package io.orange.mercadolivre.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.orange.mercadolivre.request.NewUserLoginRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;



@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
class UserLoginControllerTest {

    @PersistenceContext
    private EntityManager manager;

    static String ML_API = "/mercadolivre/usuario";

    @Autowired
    MockMvc mvc;

    @Test
    @Transactional
    @DisplayName("Must create a user successfully")
    @WithMockUser(value="fulano@ciclano.com.br")
    public void userControllerTest() throws Exception{


        String json = new ObjectMapper().writeValueAsString(new NewUserLoginRequest("fulano@ciclano.com.br","123456"));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(ML_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").value("fulano@ciclano.com.br"));
//                .andExpect(jsonPath("$[0].status").isNumber());
    }

    @Test
    @DisplayName("Must return 400 error for duplicate email")
    @Transactional
    @WithMockUser(value = "fulano@ciclano.com.br")
    public void noDuplicateEmail() throws Exception{
        String json = new ObjectMapper().writeValueAsString(new NewUserLoginRequest("fulano@ciclano.com.br", "123456"));
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(ML_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
        mvc
                .perform(request)
                .andExpect(status().isOk());
        mvc
                .perform(request)
                .andExpect(status().isBadRequest());
    }

    @WithMockUser(value="fulano@ciclano.com.br")
    @DisplayName(" Must authenticate user successfully ")
    @Test
    public void userAuthentication() throws Exception{

        mvc.perform(get("/mercadolivre/usuario").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}