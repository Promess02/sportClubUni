package mikolaj.project.backendapp.repo;


import mikolaj.project.backendapp.model.User;

import java.util.Optional;

public interface UserRepo extends RepoTemplate<User>{
    Boolean existsByEmailIgnoreCase(String email);
    Optional<User> findByEmailIgnoreCase(String emailId);
}
