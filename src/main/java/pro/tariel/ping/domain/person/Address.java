package pro.tariel.ping.domain.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private int number;
    private String street;
    private String City;
    private String zipCode;
}
