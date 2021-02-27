package io.orange.mercadolivre.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.orange.mercadolivre.entity.User;
import io.orange.mercadolivre.request.NewUserRequest;
import javassist.tools.web.BadHttpRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;



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
    @DisplayName("Must return 400 error for duplicate email")
    @Transactional
    public void noDuplicateEmail() throws Exception{
        String json = new ObjectMapper().writeValueAsString(new NewUserRequest("fulano@ciclano.com.br", "123456"));
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
}