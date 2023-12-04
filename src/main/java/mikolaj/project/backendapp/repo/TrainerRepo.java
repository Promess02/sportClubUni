package mikolaj.project.backendapp.repo;


import mikolaj.project.backendapp.model.Trainer;
import mikolaj.project.backendapp.model.User;

import java.util.Optional;

public interface TrainerRepo extends RepoTemplate<Trainer>{
    Optional<Trainer> findTrainerByUserId(Integer userId);

    Optional<Trainer> findTrainerByUser(User user);
}
