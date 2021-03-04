package io.orange.mercadolivre.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.orange.mercadolivre.registerCategory.Category;
import io.orange.mercadolivre.util.MockBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("dev")
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@SpringBootTest
public class CategoryTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    EntityManager manager;

    static String ML_API = "/mercadolivre/categories";

    @Transactional
    @Test
    @DisplayName("Must register one category and return id and name and status 200")
    @WithUserDetails("teste@logado.com")
    public void returnCategoryCreated() throws Exception {

        String json = new ObjectMapper().writeValueAsString(new Category("categoria"));
        mvc.perform(MockBuilder.run(ML_API, json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("2"))
                .andExpect(jsonPath("name").value("categoria"));

    }
}
