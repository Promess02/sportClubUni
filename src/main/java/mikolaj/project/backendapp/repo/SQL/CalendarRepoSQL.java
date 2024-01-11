package mikolaj.project.backendapp.repo.SQL;


import mikolaj.project.backendapp.model.Activity;
import mikolaj.project.backendapp.model.Calendar;
import mikolaj.project.backendapp.model.Member;
import mikolaj.project.backendapp.model.Trainer;
import mikolaj.project.backendapp.repo.CalendarRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CalendarRepoSQL extends JpaRepository<Calendar, Long>, CalendarRepo {
    @Override
    List<Calendar> findCalendarsByTrainer(Trainer trainer);
    @Override
    List<Calendar> findCalendarsByTrainerAndDate(Trainer trainer, LocalDate date);

    @Override
    List<Calendar> findCalendarsByMember(Member member);
    @Override
    List<Calendar> findCalendarsByMemberAndDate(Member member, LocalDate date);
    @Override
    List<Calendar> findCalendarsByActivityAndMember(Activity activity, Member member);
    @Override
    List<Calendar> findCalendarsByActivityAndMemberAndDate(Activity activity, Member member, LocalDate date);
}
