package mikolaj.project.backendapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id", nullable = false)
    private Integer id;

    @Column(name = "Specialization", nullable = false)
    private String specialization;

    @Column(name = "grade", nullable = false)
    private Double grade;

    @Column(name = "sum_of_grades", nullable = false)
    private Integer sumOfGrades;

    @Column(name = "num_of_grades", nullable = false)
    private Integer numOfGrades;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void addGrade(Byte grade){
        sumOfGrades = sumOfGrades+grade;
        numOfGrades++;
        this.grade = (double) sumOfGrades/numOfGrades;
    }

    public void changeGrade(Byte oldGrade, Byte newGrade){
        sumOfGrades = sumOfGrades - oldGrade + newGrade;
        this.grade = (double) sumOfGrades/numOfGrades;
    }

    public Trainer() {
    }


    public Trainer(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return getUser().getName() + " " + getUser().getSurname();
    }
}