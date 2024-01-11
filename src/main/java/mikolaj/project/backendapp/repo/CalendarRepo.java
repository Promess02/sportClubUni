package mikolaj.project.backendapp.repo;

import mikolaj.project.backendapp.model.Activity;
import mikolaj.project.backendapp.model.Calendar;
import mikolaj.project.backendapp.model.Member;
import mikolaj.project.backendapp.model.Trainer;

import java.time.LocalDate;
import java.util.List;

public interface CalendarRepo extends RepoTemplate<Calendar>{
    List<Calendar> findCalendarsByTrainer(Trainer trainer);
    List<Calendar> findCalendarsByMember(Member member);
    List<Calendar> findCalendarsByTrainerAndDate(Trainer trainer, LocalDate date);
    List<Calendar> findCalendarsByMemberAndDate(Member member, LocalDate date);
    List<Calendar> findCalendarsByActivityAndMember(Activity activity, Member member);
    List<Calendar> findCalendarsByActivityAndMemberAndDate(Activity activity, Member member, LocalDate date);
}
