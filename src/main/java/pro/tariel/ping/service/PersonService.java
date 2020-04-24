package pro.tariel.ping.service;

import pro.tariel.ping.domain.person.Person;

import java.util.Collection;

public interface PersonService {
    Person getPersonById(long id);
    Collection<Person> getAllPersons();
}
