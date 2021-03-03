package io.orange.mercadolivre.util;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class MockBuilder {

    public static MockHttpServletRequestBuilder run(String ML_API, String json){
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(ML_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
        return request;
    }

}
