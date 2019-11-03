package poc.openapi.client.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import poc.openapi.client.service.OpenApiClientService;
import poc.openapi.swagger.model.Pet;

@RestController
@Slf4j
public class OpenApiClientController {

    @Autowired
    private OpenApiClientService service;

    @PostMapping(consumes = "application/json", produces = "application/json", path = "/feign")
    public ResponseEntity<Pet> addFeign(@Valid @RequestBody Pet pet) {
        log.info("add: {}", pet);
        return new ResponseEntity<>(service.feignAdd(pet), HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json", path = "/feign/{petId}")
    public ResponseEntity<Pet> getFeign(@PathVariable("petId") String petId) {
        return new ResponseEntity<>(service.feignGet(petId), HttpStatus.OK);
    }
    
    @PostMapping(consumes = "application/json", produces = "application/json", path = "/rest")
    public ResponseEntity<Pet> addRest(@Valid @RequestBody Pet pet) {
        log.info("add: {}", pet);
        return new ResponseEntity<>(service.restAdd(pet), HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json", path = "/rest/{petId}")
    public ResponseEntity<Pet> getRest(@PathVariable("petId") String petId) {
        return new ResponseEntity<>(service.restGet(petId), HttpStatus.OK);
    }

}