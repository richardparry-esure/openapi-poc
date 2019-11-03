package poc.openapi.client.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import poc.openapi.swagger.model.Pet;

@Service
@RequiredArgsConstructor
public class OpenApiClientService {

    private final OpenApiFeignClient feign;
    private final OpenApiRestTemplate rest;

    public Pet feignAdd(final Pet pet) {
        return feign.createPet(pet);
    }

    public Pet feignGet(final String id) {
        return feign.getPetById(id);
    }
    
    public Pet restAdd(final Pet pet) {
        return rest.createPet(pet);
    }

    public Pet restGet(final String id) {
        return rest.getPetById(id);
    }

}
