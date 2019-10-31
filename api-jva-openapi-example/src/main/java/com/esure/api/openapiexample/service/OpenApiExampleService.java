package com.esure.api.openapiexample.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.esure.api.openapi.swagger.model.Cat;
import com.esure.api.openapi.swagger.model.Cat.HuntingSkillEnum;
import com.esure.api.openapi.swagger.model.Dog;
import com.esure.api.openapi.swagger.model.Pet;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OpenApiExampleService {
    private int latestId = 3;
    
    private Map<String, Pet> map = new HashMap<>();


    public String add(final Pet pet) {
        log.info("add: {}", pet);
        final String id = String.valueOf(latestId++);
        map.put(id, pet);
        return id;
    }
    
    public Pet getById(String id) {
        return map.get(id);
    }
    
    public Collection<Pet> getAll() {
        return map.values();
    }

    
    @PostConstruct
    public void setupTestData() {
        map.put("1", new Cat().huntingSkill(HuntingSkillEnum.LAZY).name("Garfield").petType("cat"));
        map.put("2", new Dog().packSize(3).name("Scooby").petType("dog"));
        map.put("3", new Pet().name("bob").petType("bob"));
    }
}
