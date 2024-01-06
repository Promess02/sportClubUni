package mikolaj.project.backendapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "max_capacity", nullable = false)
    private Integer maxCapacity;

    @Column(name = "current_capacity", nullable = false)
    private Integer currentCapacity;

    @Column(name = "week_day_open_time", nullable = false)
    private LocalTime weekDayOpenTime;

    @Column(name = "week_day_close_time", nullable = false)
    private LocalTime weekDayCloseTime;

    @Column(name = "weekend_open_time", nullable = false)
    private LocalTime weekendOpenTime;

    @Column(name = "weekend_close_time", nullable = false)
    private LocalTime weekendCloseTime;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location location)) return false;
        return Objects.equals(id, location.id) && Objects.equals(name, location.name) && Objects.equals(maxCapacity, location.maxCapacity) && Objects.equals(currentCapacity, location.currentCapacity) && Objects.equals(weekDayOpenTime, location.weekDayOpenTime) && Objects.equals(weekDayCloseTime, location.weekDayCloseTime) && Objects.equals(weekendOpenTime, location.weekendOpenTime) && Objects.equals(weekendCloseTime, location.weekendCloseTime) && Objects.equals(address, location.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, maxCapacity, currentCapacity, weekDayOpenTime, weekDayCloseTime, weekendOpenTime, weekendCloseTime, address);
    }
}