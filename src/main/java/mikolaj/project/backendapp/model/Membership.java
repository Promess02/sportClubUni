package mikolaj.project.backendapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mikolaj.project.backendapp.enums.MembershipStatus;
import mikolaj.project.backendapp.persistence.MembershipStatusConverter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "membership_status", nullable = false)
    @Convert(converter = MembershipStatusConverter.class)
    private MembershipStatus membershipStatus = MembershipStatus.NEVER_ACQUIRED;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "membership_type_id", nullable = false)
    private MembershipType membershipType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "activity_id")
    private Activity activity;

    public Membership() {
        startDate = LocalDate.now();
    }

    public Membership(LocalDate endDate, MembershipStatus membershipStatus, Member member, MembershipType membershipType, Activity activity) {
        this.startDate = LocalDate.now();
        this.endDate = endDate;
        this.membershipStatus = membershipStatus;
        this.member = member;
        this.membershipType = membershipType;
        this.activity = activity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Membership that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && membershipStatus == that.membershipStatus && Objects.equals(member, that.member) && Objects.equals(membershipType, that.membershipType) && Objects.equals(activity, that.activity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, membershipStatus, member, membershipType, activity);
    }
}