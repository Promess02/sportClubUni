package mikolaj.project.backendapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mikolaj.project.backendapp.enums.Sport;
import mikolaj.project.backendapp.persistence.SportConverter;

@Getter
@Setter
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "max_members", nullable = false)
    private Integer maxMembers;

    @Column(name = "Logo_icon_url", nullable = true)
    private String logoIconUrl;

    @Column(name = "sport", nullable = false)
    @Convert(converter = SportConverter.class)
    private Sport sport;

    @Override
    public String toString() {
        return getName();
    }
}