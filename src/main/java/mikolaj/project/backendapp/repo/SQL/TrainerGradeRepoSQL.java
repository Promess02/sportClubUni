package mikolaj.project.backendapp.repo.SQL;

import mikolaj.project.backendapp.model.Member;
import mikolaj.project.backendapp.model.Trainer;
import mikolaj.project.backendapp.model.TrainerGrade;
import mikolaj.project.backendapp.repo.TrainerGradeRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerGradeRepoSQL extends JpaRepository<TrainerGrade, Integer>, TrainerGradeRepo {
    @Override
    Optional<TrainerGrade> findTrainerGradeByMemberAndTrainer(Member member, Trainer trainer);
}
