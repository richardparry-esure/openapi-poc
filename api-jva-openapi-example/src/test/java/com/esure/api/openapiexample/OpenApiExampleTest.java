package com.esure.api.openapiexample;
import static com.esure.api.openapiexample.test.utils.DocumentorUtils.*;
import static com.esure.apidocs.utils.PathUtils.getMethodPath;
import static com.esure.apidocs.utils.PathUtils.getRequestJsonSnippetName;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.IOException;
import java.lang.reflect.Method;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMethod;

import com.esure.api.openapi.swagger.model.CreateResponse;
import com.esure.api.openapi.swagger.model.Dog;
import com.esure.api.openapi.swagger.model.Pet;
import com.esure.api.openapiexample.api.OpenApiExampleController;
import com.esure.api.openapiexample.test.utils.ApplicationCT;
import com.esure.apidocs.BeanJsonDocumentor;
import com.esure.apidocs.Scenario;
import com.esure.apidocs.ScenariosDocumentor;
import com.esure.apidocs.utils.PathUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OpenApiExampleTest extends ApplicationCT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;
    private static Method method;

    @BeforeClass
    public static void beforeTests() throws Exception {
        method = OpenApiExampleController.class.getDeclaredMethod("createPet", Pet.class);
//        GetRequestDocumentor documentor = new GetRequestDocumentor(restDocumentation, method, new HashMap<>());
//        documentor.document();
    }

    @AfterClass
    public static void afterTestsComplete() throws Exception {
        scenariosDocumentor.document();
    }

    @Test
    public void document_create_dog() throws Exception {
        final String requestJson = "{\"petType\": \"Dog\",\"name\": \"Bob\",\"packSize\" : 4}";
        final MockHttpServletResponse response = mvc.perform(
                post("/pets")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson)
         )
        .andDo(print())
        .andReturn()
        .getResponse();
        
        final String responseAsString = response.getContentAsString();
        final CreateResponse createResponse = objectMapper.readValue(responseAsString, CreateResponse.class);

        documentResponseBean(createResponse);
        documentScenario(response, createResponse, "", "Success response", 0);
        addBeanDocumentation(restDocumentation, "Dog",
                getRequestJsonSnippetName("createPet"),
                new Dog().packSize(4).name("bob").petType("Dog"),
                getMethodPath(OpenApiExampleController.class, "createPet"));
    }


   

 
    private void documentResponseBean(final CreateResponse response) throws IOException {
        BeanJsonDocumentor<CreateResponse> respDocumentor = new BeanJsonDocumentor<>(restDocumentation,
                "response", PathUtils.getMethodPath(OpenApiExampleController.class, method.getName()),
                PathUtils.getResponseJsonSnippetName(method.getName()), response);
        respDocumentor.document();
    }

    private void documentScenario(final MockHttpServletResponse mockHttpServletResponse, final CreateResponse response,
                                  String requestString, String s, int position) throws JsonProcessingException {
        Scenario<String, CreateResponse> scenario = new Scenario<>(s,
                RequestMethod.POST, ScenariosDocumentor.ERROR_CODE_NA, HttpStatus.valueOf(mockHttpServletResponse.getStatus()), requestString,
                response);
        scenariosDocumentor.addScenario(scenario, method, position);
    }
}
