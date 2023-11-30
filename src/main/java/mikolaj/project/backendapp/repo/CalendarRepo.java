package mikolaj.project.backendapp.repo;
import mikolaj.project.backendapp.model.Calendar;
import mikolaj.project.backendapp.model.Trainer;

import java.util.List;
import java.util.Optional;

public interface CalendarRepo extends RepoTemplate<Calendar>{
    List<Calendar> findCalendarsByTrainer(Trainer trainer);
}
