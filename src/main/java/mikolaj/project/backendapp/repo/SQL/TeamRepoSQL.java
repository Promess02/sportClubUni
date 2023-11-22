package mikolaj.project.backendapp.repo.SQL;

import mikolaj.project.backendapp.model.Team;
import mikolaj.project.backendapp.repo.TeamRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepoSQL extends JpaRepository<Team, Long>, TeamRepo {
}
