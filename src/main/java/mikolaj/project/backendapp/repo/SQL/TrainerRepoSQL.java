package mikolaj.project.backendapp.repo.SQL;

import mikolaj.project.backendapp.model.Member;
import mikolaj.project.backendapp.model.Trainer;
import mikolaj.project.backendapp.repo.TrainerRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepoSQL extends JpaRepository<Trainer, Long>, TrainerRepo {
    @Override
    Optional<Trainer> findTrainerByUserId(Integer userId);
}
