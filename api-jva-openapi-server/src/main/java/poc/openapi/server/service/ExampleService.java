package poc.openapi.server.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import poc.openapi.swagger.model.Cat;
import poc.openapi.swagger.model.Cat.HuntingSkillEnum;
import poc.openapi.swagger.model.Dog;
import poc.openapi.swagger.model.Pet;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExampleService {
    private int latestId = 3;
    
    private Map<String, Pet> map = new HashMap<>();


    public Pet add(final Pet pet) {
        log.info("add: {}", pet);
        pet.id(String.valueOf(++latestId));
        map.put(pet.getId(), pet);
        return pet;
    }
    
    public Pet getById(String id) {
        return map.get(id);
    }
    
    public Collection<Pet> getAll() {
        return map.values();
    }

    
    @PostConstruct
    public void setupTestData() {
        map.put("1", new Cat().huntingSkill(HuntingSkillEnum.LAZY).name("Garfield").petType("Cat"));
        map.put("2", new Dog().packSize(3).name("Scooby").petType("Dog"));
        map.put("3", new Pet().name("Peppa").petType("Pig"));
    }
}
