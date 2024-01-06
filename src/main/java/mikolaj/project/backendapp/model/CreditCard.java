package mikolaj.project.backendapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mikolaj.project.backendapp.persistence.LocalDateConverter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity(name = "credit_card")
//TODO - fix LocalDateConverter so that credit card can be viewed in json
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(number, that.number) && Objects.equals(cvv, that.cvv) && Objects.equals(expirationDate, that.expirationDate) && Objects.equals(bank, that.bank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, cvv, expirationDate, bank);
    }
}