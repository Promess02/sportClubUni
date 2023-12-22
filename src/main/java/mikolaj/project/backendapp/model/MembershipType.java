package mikolaj.project.backendapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "membership_type")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MembershipType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "months", nullable = false)
    private Byte months;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "full_access", nullable = false)
    private Boolean fullAccess = false;


    @Column(name = "discount")
    private Double discount;

    @Override
    public String toString() {
        return getName();
    }
}