package mikolaj.project.backendapp.repo;

import mikolaj.project.backendapp.model.Member;
import mikolaj.project.backendapp.model.Trainer;
import mikolaj.project.backendapp.model.TrainerGrade;

import java.util.Optional;

public interface TrainerGradeRepo extends RepoTemplate<TrainerGrade>{
    Optional<TrainerGrade> findTrainerGradeByMemberAndTrainer(Member member, Trainer trainer);
}
