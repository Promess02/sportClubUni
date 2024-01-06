package mikolaj.project.backendapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mikolaj.project.backendapp.enums.Sport;
import mikolaj.project.backendapp.persistence.SportConverter;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team team)) return false;
        return Objects.equals(id, team.id) && Objects.equals(name, team.name) && Objects.equals(maxMembers, team.maxMembers) && Objects.equals(logoIconUrl, team.logoIconUrl) && sport == team.sport;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, maxMembers, logoIconUrl, sport);
    }

    @Override
    public String toString() {
        return getName();
    }
}