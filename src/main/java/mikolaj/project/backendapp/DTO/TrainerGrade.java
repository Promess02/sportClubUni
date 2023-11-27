package mikolaj.project.backendapp.DTO;

import lombok.Data;
import mikolaj.project.backendapp.model.Member;
import mikolaj.project.backendapp.model.Trainer;

@Data
public class TrainerGrade {
    private Trainer trainer;
    private Byte grade;
    private Member member;
}
