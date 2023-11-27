package mikolaj.project.backendapp.repo.SQL;

import mikolaj.project.backendapp.model.User;
import mikolaj.project.backendapp.repo.UserRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepoSQL extends JpaRepository<User, Long>, UserRepo {
    @Override
    User findByEmailIgnoreCase(String emailId);
    @Override
    Boolean existsByEmailIgnoreCase(String email);
}
