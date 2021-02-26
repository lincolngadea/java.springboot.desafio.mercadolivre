package io.orange.mercadolivre.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.orange.mercadolivre.entity.User;
import io.orange.mercadolivre.request.NewUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
class UserControllerTest {

    @PersistenceContext
    private EntityManager manager;

    static String ML_API = "/mercadolivre/usuario";

    @Autowired
    MockMvc mvc;

    @Test
    @Transactional
    @DisplayName("Must create a user successfully")
    public void userControllerTest() throws Exception{

        String json = new ObjectMapper().writeValueAsString(new NewUserRequest("fulano@ciclano.com.br","123456"));

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
    @DisplayName("Must return the encrypted password ")
    void returnEncyptPassword() throws Exception{
        User pass = new User();
        String encryptPass = "e10adc3949ba59abbe56e057f20f883e";
        Assertions.assertEquals(encryptPass,pass.encryptPass("123456"));

    }
}

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})