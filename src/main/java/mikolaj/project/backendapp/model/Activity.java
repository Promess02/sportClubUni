package mikolaj.project.backendapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mikolaj.project.backendapp.enums.Sport;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "minutes", nullable = false)
    private Integer minutes;

    @Column(name = "description")
    private String description;

    @Column(name = "sport", nullable = false)
    private Sport sport;

    @Column(name = "currentMembers", nullable = false)
    private Integer currentMembers = 0;

    @Column(name = "memberLimit")
    private Integer memberLimit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LocationId", nullable = false)
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TrainerId", nullable = false)
    private Trainer trainer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TeamId")
    private Team team;

    public Activity() {
    }
    public Activity(String name, LocalDate date, LocalTime time,
                    Integer minutes, String description, Sport sport,
                    Integer currentMembers, Integer memberLimit, Location location,
                    Trainer trainer, Team team) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.minutes = minutes;
        this.description = description;
        this.sport = sport;
        this.currentMembers = currentMembers;
        this.memberLimit = memberLimit;
        this.location = location;
        this.trainer = trainer;
        this.team = team;
    }
}