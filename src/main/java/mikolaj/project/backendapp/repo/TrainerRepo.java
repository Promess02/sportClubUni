package mikolaj.project.backendapp.repo;


import mikolaj.project.backendapp.model.Member;
import mikolaj.project.backendapp.model.Trainer;

import java.util.Optional;

public interface TrainerRepo extends RepoTemplate<Trainer>{
    Optional<Trainer> findTrainerByUserId(Integer userId);
}
