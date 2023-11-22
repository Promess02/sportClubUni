package mikolaj.project.backendapp.repo.SQL;


import mikolaj.project.backendapp.model.Location;
import mikolaj.project.backendapp.repo.LocationRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepoSQL extends JpaRepository<Location, Long>, LocationRepo {
}
