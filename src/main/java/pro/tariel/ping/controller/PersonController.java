package pro.tariel.ping.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.tariel.ping.domain.person.PersonDto;
import pro.tariel.ping.service.PersonService;

import java.util.List;

@RestController
public class PersonController {
    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping(value = "/persons", produces = "application/json")
    public List<PersonDto> retrieveAllPersons() {
        return service.getAllPersons();
    }

    @GetMapping(value = "/person/{id}", produces = "application/json")
    public ResponseEntity<PersonDto> retrievePersonById(@PathVariable long id) {
        if(id < 7 || id > 12) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.getPersonById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/person", produces = "application/json")
    public ResponseEntity<String> createPerson() {
        return new ResponseEntity<>("Person is created successfully", HttpStatus.OK);
    }
}
