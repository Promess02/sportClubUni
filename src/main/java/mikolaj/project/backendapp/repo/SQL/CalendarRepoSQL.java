package mikolaj.project.backendapp.repo.SQL;


import mikolaj.project.backendapp.model.Calendar;
import mikolaj.project.backendapp.model.Trainer;
import mikolaj.project.backendapp.repo.CalendarRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarRepoSQL extends JpaRepository<Calendar, Long>, CalendarRepo {
    @Override
    List<Calendar> findCalendarsByTrainer(Trainer trainer);
}
