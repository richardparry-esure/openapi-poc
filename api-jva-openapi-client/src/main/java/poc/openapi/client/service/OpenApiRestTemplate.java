package poc.openapi.client.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import poc.openapi.swagger.model.Pet;

@RequiredArgsConstructor
@Service
public class OpenApiRestTemplate {
    private final RestTemplate rest;
    private static final String URL = "http://localhost:9086/api-jva-openapi-server/v1/pets";

    
    public Pet createPet(final Pet pet) {
        ResponseEntity<Pet> response = rest.postForEntity(URL, pet, Pet.class);
        return handleResponse(response);
    }
    
     public Pet getPetById(final String petId) {
         Map<String, Object> params = new HashMap<>();
         params.put("petId", petId);
         ResponseEntity<Pet> response = rest.getForEntity(URL +"/{petId}", Pet.class, params);
         return handleResponse(response);
     }
     
     private Pet handleResponse(final ResponseEntity<Pet> response) {
         if (response.getBody() != null) {
             return response.getBody();
         } else {
             throw new RuntimeException("no response body");
         }
     }
}
