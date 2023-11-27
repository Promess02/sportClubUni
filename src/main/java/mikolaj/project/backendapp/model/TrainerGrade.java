package mikolaj.project.backendapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mikolaj.project.backendapp.model.Member;
import mikolaj.project.backendapp.model.Trainer;

@Getter
@Setter
@Entity
@Table(name = "trainer_grades")
public class TrainerGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_grade_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @Column(name = "grade", nullable = false)
    private Byte grade;

    public TrainerGrade(Member member, Trainer trainer, Byte grade) {
        this.member = member;
        this.trainer = trainer;
        this.grade = grade;
    }
}