package poc.openapi.client.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import poc.openapi.swagger.model.Pet;

@FeignClient(name = "openapi-server", url = "http://localhost:9086", path = "/api-jva-openapi-server/v1")
public interface OpenApiFeignClient {

    @PostMapping(consumes = "application/json", produces = "application/json", path = "/pets")
    Pet createPet(@Valid @NotNull @RequestBody Pet pet);

    @GetMapping(value = "/pets/{petId}", consumes = "application/json")
    Pet getPetById(@PathVariable("petId") String petId);
}
