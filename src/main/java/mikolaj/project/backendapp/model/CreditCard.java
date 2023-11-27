package mikolaj.project.backendapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mikolaj.project.backendapp.persistence.LocalDateConverter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
//TODO - fix LocalDateConverter so that credit card can be viewed in json
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "number", nullable = false)
    private Long number;

    @Column(name = "cvv", nullable = false)
    private Integer cvv;

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "bank")
    private String bank;

    public CreditCard(Long number, Integer cvv, LocalDate expirationDate, String bank) {
        this.number = number;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.bank = bank;
    }

    public CreditCard() {

    }
}