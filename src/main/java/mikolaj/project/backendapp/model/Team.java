package mikolaj.project.backendapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mikolaj.project.backendapp.enums.Sport;

@Getter
@Setter
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "maxMembers", nullable = false)
    private Integer maxMembers;

    @Column(name = "LogoIconUrl", nullable = false)
    private String logoIconUrl;

    @Column(name = "sport", nullable = false)
    private Sport sport;

}