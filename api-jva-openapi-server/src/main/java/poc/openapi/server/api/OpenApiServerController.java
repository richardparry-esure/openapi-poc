package poc.openapi.server.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import poc.openapi.server.service.ExampleService;
import poc.openapi.swagger.api.PetsApi;
import poc.openapi.swagger.model.Pet;
import poc.openapi.swagger.model.Pets;

/**
 * REST controller to process received requests.
 * 
 * 
 */
@RestController
@Slf4j
public class OpenApiServerController implements PetsApi {
    @Autowired
    private ExampleService service;
    
    @Override
    public ResponseEntity<Pet> createPet( @Valid @RequestBody final Pet pet) {
        log.info("createPet {}", pet);
        ResponseEntity<Pet> response = ResponseEntity.status(HttpStatus.CREATED).body(service.add(pet));
        log.info("createPet response {}", response);
        return  response;

    }


    @Override
    public ResponseEntity<Pets> listPets(@Valid @RequestParam(value = "limit", required = false) Integer limit) {
        Pets pets = new Pets();
        pets.addAll(service.getAll());
        return ResponseEntity.ok(pets);
    }


    @Override
    public ResponseEntity<Pet> showPetById( @PathVariable("petId") String petId) {
        log.info("showPetById {}", petId);
        ResponseEntity<Pet> response = ResponseEntity.ok(service.getById(petId));
        log.info("showPetById response {}", response);
        return  response;
    }

}