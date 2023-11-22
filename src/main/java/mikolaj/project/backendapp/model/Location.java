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

    @Column(name = "maxCapacity", nullable = false)
    private Integer maxCapacity;

    @Column(name = "currentCapacity", nullable = false)
    private Integer currentCapacity;

    @Column(name = "weekDayOpenTime", nullable = false)
    private LocalTime weekDayOpenTime;

    @Column(name = "weekDayCloseTime", nullable = false)
    private LocalTime weekDayCloseTime;

    @Column(name = "weekendOpenTime", nullable = false)
    private LocalTime weekendOpenTime;

    @Column(name = "weekendCloseTime", nullable = false)
    private LocalTime weekendCloseTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AddressId", nullable = false)
    private Address address;

}