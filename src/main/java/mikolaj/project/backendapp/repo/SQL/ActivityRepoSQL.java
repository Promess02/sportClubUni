package mikolaj.project.backendapp.repo.SQL;

import mikolaj.project.backendapp.model.Activity;
import mikolaj.project.backendapp.model.Location;
import mikolaj.project.backendapp.repo.ActivityRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepoSQL extends JpaRepository<Activity, Integer>, ActivityRepo {
    @Override
    Optional<Activity> findActivityByName(String name);
    @Override
    Optional<Activity> findActivityByDate(LocalDate date);
    @Override
    List<Activity> findActivitiesByLocationAndDate(Location location, LocalDate date);
}
