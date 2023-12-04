package mikolaj.project.backendapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "City", nullable = false)
    private String city;

    @Column(name = "Street")
    private String street;

    @Column(name = "house_number", nullable = false)
    private String houseNumber;

    @Column(name = "post_code", nullable = false)
    private String postCode;

}