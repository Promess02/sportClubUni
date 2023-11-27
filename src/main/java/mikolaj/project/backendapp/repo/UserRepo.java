package mikolaj.project.backendapp.repo;


import mikolaj.project.backendapp.model.User;

public interface UserRepo extends RepoTemplate<User>{
    Boolean existsByEmailIgnoreCase(String email);
    User findByEmailIgnoreCase(String emailId);
}
