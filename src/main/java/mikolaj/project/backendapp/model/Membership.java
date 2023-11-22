package mikolaj.project.backendapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;

    @Column(name = "endDate", nullable = false)
    private LocalDate endDate;

    @Column(name = "membershipStatus", nullable = false)
    private Boolean membershipStatus = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MemberId", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MembershipTypeId", nullable = false)
    private MembershipType membershipType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ActivityId")
    private Activity activity;

    public Membership() {
        startDate = LocalDate.now();
    }

    public Membership(LocalDate endDate, Boolean membershipStatus, Member member, MembershipType membershipType, Activity activity) {
        this.startDate = LocalDate.now();
        this.endDate = endDate;
        this.membershipStatus = membershipStatus;
        this.member = member;
        this.membershipType = membershipType;
        this.activity = activity;
    }
}