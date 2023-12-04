package mikolaj.project.backendapp.repo;

import mikolaj.project.backendapp.model.Calendar;
import mikolaj.project.backendapp.model.Member;
import mikolaj.project.backendapp.model.Trainer;

import java.util.List;

public interface CalendarRepo extends RepoTemplate<Calendar>{
    List<Calendar> findCalendarsByTrainer(Trainer trainer);
    List<Calendar> findCalendarsByMember(Member member);
}
