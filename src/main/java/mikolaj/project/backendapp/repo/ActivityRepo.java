package mikolaj.project.backendapp.repo;

import mikolaj.project.backendapp.model.Activity;

import java.time.LocalDate;
import java.util.Optional;

public interface ActivityRepo extends RepoTemplate<Activity>{
    Optional<Activity> findActivityByName(String name);
    Optional<Activity> findActivityByDate(LocalDate date);

}
