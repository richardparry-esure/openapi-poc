package com.esure.api.openapiexample.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esure.api.openapi.swagger.api.PetsApi;
import com.esure.api.openapi.swagger.model.CreateResponse;
import com.esure.api.openapi.swagger.model.Pet;
import com.esure.api.openapi.swagger.model.Pets;
import com.esure.api.openapiexample.service.OpenApiExampleService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * REST controller to process received requests.
 * 
 * 
 */
@RestController
@Slf4j
public class OpenApiExampleController implements PetsApi {
    @Autowired
    private OpenApiExampleService service;
    
    @Override
    public ResponseEntity<CreateResponse> createPet( @Valid @RequestBody final Pet pet) {
        log.info("pet {}", pet);
        return  ResponseEntity.status(HttpStatus.CREATED).body(new CreateResponse().id( service.add(pet)));

    }


    @Override
    public ResponseEntity<Pets> listPets(@Valid @RequestParam(value = "limit", required = false) Integer limit) {
        Pets pets = new Pets();
        pets.addAll(service.getAll());
        return ResponseEntity.ok(pets);
    }


    @Override
    public ResponseEntity<Pet> showPetById( @PathVariable("petId") String petId) {
        return ResponseEntity.ok(service.getById(petId));
    }

}