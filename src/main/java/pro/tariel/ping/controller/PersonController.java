package pro.tariel.ping.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pro.tariel.ping.domain.person.Person;
import pro.tariel.ping.service.PersonService;

import java.util.Collection;

@RestController
public class PersonController {
    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping(value = "/persons", produces = "application/json")
    public Collection<Person> retrieveAllPersons() {
        return service.getAllPersons();
    }

    @GetMapping(value = "/person/{id}", produces = "application/json")
    public ResponseEntity<Person> retrievePersonById(@PathVariable long id) {
        return new ResponseEntity<>(service.getPersonById(id), HttpStatus.OK);
    }
}
