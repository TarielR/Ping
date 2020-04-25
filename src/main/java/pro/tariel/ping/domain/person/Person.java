package pro.tariel.ping.domain.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    //private Person spouse;
    private Long spouseId;
    private List<Person> children;
    private Address address;
}
