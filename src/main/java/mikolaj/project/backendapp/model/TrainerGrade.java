package mikolaj.project.backendapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "trainer_grades")
public class TrainerGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_grade_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @Column(name = "grade", nullable = false)
    private Byte grade;

    public TrainerGrade() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainerGrade that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(member, that.member) && Objects.equals(trainer, that.trainer) && Objects.equals(grade, that.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, member, trainer, grade);
    }

    public TrainerGrade(Member member, Trainer trainer, Byte grade) {
        this.member = member;
        this.trainer = trainer;
        this.grade = grade;
    }
}