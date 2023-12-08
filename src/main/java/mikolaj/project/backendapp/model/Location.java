package mikolaj.project.backendapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

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

}