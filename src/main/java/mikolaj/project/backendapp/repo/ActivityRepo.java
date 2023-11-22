package mikolaj.project.backendapp.repo;

import mikolaj.project.backendapp.model.Activity;
import java.util.Optional;

public interface ActivityRepo extends RepoTemplate<Activity>{
    Optional<Activity> findActivityByName(String name);
}
