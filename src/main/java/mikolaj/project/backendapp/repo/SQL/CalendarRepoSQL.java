package mikolaj.project.backendapp.repo.SQL;


import mikolaj.project.backendapp.model.Calendar;
import mikolaj.project.backendapp.model.Member;
import mikolaj.project.backendapp.model.Trainer;
import mikolaj.project.backendapp.repo.CalendarRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepoSQL extends JpaRepository<Calendar, Long>, CalendarRepo {
    @Override
    List<Calendar> findCalendarsByTrainer(Trainer trainer);

    @Override
    List<Calendar> findCalendarsByMember(Member member);
}
