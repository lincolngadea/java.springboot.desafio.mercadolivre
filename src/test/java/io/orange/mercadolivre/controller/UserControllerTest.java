package io.orange.mercadolivre.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
class UserControllerTest {


    static String ML_API = "/mercadolivre/usuario";

    @Autowired
    MockMvc mvc;



    @Test
    @DisplayName("Must create a user successfully")
    void userControllerTest(){

    }

    @Test
    @DisplayName("Mus validate the email format for login")
    void loginFormatValidate(){

    }

}