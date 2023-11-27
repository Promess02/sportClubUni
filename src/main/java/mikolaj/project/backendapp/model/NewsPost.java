package mikolaj.project.backendapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class NewsPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "dateOfPosting", nullable = false)
    private LocalDate dateOfPosting;

    @Column(name = "Content", nullable = false)
    private String content;

    @Column(name = "ImageUrl", nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LocationId")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MembershipTypeId")
    private MembershipType membershipType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ActivityId")
    private Activity activity;

    public NewsPost() {
        this.dateOfPosting = LocalDate.now();
    }

    public NewsPost(String name, String content, String imageUrl, Location location, MembershipType membershipType, Activity activity) {
        this.name = name;
        this.dateOfPosting = LocalDate.now();
        this.content = content;
        this.imageUrl = imageUrl;
        this.location = location;
        this.membershipType = membershipType;
        this.activity = activity;
    }
}