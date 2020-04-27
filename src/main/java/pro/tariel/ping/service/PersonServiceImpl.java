package pro.tariel.ping.service;

import org.springframework.stereotype.Service;
import pro.tariel.ping.domain.person.Address;
import pro.tariel.ping.domain.person.Person;
import pro.tariel.ping.domain.person.PersonDto;

import java.time.LocalDate;
import java.util.*;

@Service
public class PersonServiceImpl implements PersonService {

    private Map<Long, Person> map = new HashMap<>();

    {
        createPersons();
    }

    @Override
    public PersonDto getPersonById(long id) {
        Person person = map.get(id);
        String spouseName = getSpouseName(person);
        return new PersonDto(person, spouseName);
    }

    @Override
    public List<PersonDto> getAllPersons() {
        Collection<Person> col = map.values();
        Iterator<Person> itr = col.iterator();
        List<PersonDto> list = new ArrayList<>();

        while(itr.hasNext()) {
            Person p = itr.next();
            String spouseName = getSpouseName(p);
            PersonDto personDto = new PersonDto(p, spouseName);
            list.add(personDto);
        }
        return list;
    }

    private String getSpouseName(Person p) {
        if (p.getSpouseId() == null)
            return null;
        return map.get(p.getSpouseId()).getName();
    }

    private void createPersons() {
        Address address01 = new Address(1011, "Foster st.", "West Chester", "19380");
        Address address02 = new Address(202, "Grant Ave", "Philadelphia", "01001");
        Address address03 = new Address(34567, "Welsh Rd", "Malvern", "19355");
        Address address04 = new Address(3456, "Oro dr", "Exton", "19344");

        Person child01 = new Person(1L,"Anni", LocalDate.of(1999, 2, 3), null, null, null);
        Person child02 = new Person(2L,"Tom", LocalDate.of(1997, 5, 31), null, null, address01);
        Person child03 = new Person(3L,"Mike", LocalDate.of(2002, 10, 10), null, null, address01);
        Person child04 = new Person(4L,"Kate", LocalDate.of(2005, 2, 13), null, null, address04);
        Person child05 = new Person(5L,"Barbara", LocalDate.of(1998, 8, 28), null, null, address02);
        Person child06 = new Person(6L,"Andrew", LocalDate.of(2001, 11, 12), null, null, address02);

        List<Person> children01 = List.of(child01, child02, child03);
        List<Person> children02 = List.of(child05, child06);
        List<Person> children03 = List.of(child04);

        Person person01 = new Person(7L, "John Smith", LocalDate.of(1966, 9, 10), 8L, children01, address01);
        Person person02 = new Person(8L, "Yulia Smith", LocalDate.of(1969, 5, 22), 7L, children01, address01);

        Person person03 = new Person(9L, "Rosi Potter", LocalDate.of(1971, 7, 10), 10L, children02, address02);
        Person person04 = new Person(10L, "Peter Potter", LocalDate.of(1976, 9, 6), 9L, children02, address02);

        Person person05 = new Person(11L, "Don Donalds", LocalDate.of(1977, 1, 10), null, null, address03);
        Person person06 = new Person(12L, "Lisa Tramp", LocalDate.of(1961, 7, 4), null, children03, address04);

        map = Map.of(7L, person01, 8L, person02, 9L, person03, 10L, person04, 11L, person05, 12L, person06);
    }
}
