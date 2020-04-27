package pro.tariel.ping.service;

import pro.tariel.ping.domain.person.PersonDto;

import java.util.List;

public interface PersonService {
    PersonDto getPersonById(long id);
    List<PersonDto> getAllPersons();
}
