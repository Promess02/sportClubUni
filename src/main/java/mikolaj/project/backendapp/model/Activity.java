    package mikolaj.project.backendapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mikolaj.project.backendapp.enums.Sport;
import mikolaj.project.backendapp.persistence.SportConverter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

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
    @Convert(converter = SportConverter.class)
    private Sport sport;

    @Column(name = "current_members", nullable = false)
    private Integer currentMembers = 0;

    @Column(name = "member_limit")
    private Integer memberLimit;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "image_url")
    private String imageUrl;
    public Activity() {
    }
    public Activity(String name, LocalDate date, LocalTime time,
                    Integer minutes, String description, Sport sport,
                    Integer currentMembers, Integer memberLimit, Location location,
                    Trainer trainer, Team team, String imageUrl) {
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
        this.imageUrl = imageUrl;
    }
    public void signUp(){
        currentMembers += 1;
    }

    public void cancel(){
        currentMembers -= 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(id, activity.id) && Objects.equals(name, activity.name) && Objects.equals(date, activity.date) && Objects.equals(time, activity.time) && Objects.equals(minutes, activity.minutes) && Objects.equals(description, activity.description) && Objects.equals(sport, activity.getSport()) && Objects.equals(currentMembers, activity.currentMembers) && Objects.equals(memberLimit, activity.memberLimit) && Objects.equals(location, activity.location) && Objects.equals(trainer, activity.trainer) && Objects.equals(team, activity.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date, time, minutes, description, sport, currentMembers, memberLimit, location, trainer, team);
    }

    @Override
    public String toString() {
        return getName();
    }

    public boolean checkIfActivityAvailable(){
        return !Objects.equals(currentMembers, memberLimit);
    }
}