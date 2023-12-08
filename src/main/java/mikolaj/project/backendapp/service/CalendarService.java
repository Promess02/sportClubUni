package mikolaj.project.backendapp.service;

import mikolaj.project.backendapp.DTO.DateRange;
import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.model.Activity;
import mikolaj.project.backendapp.model.Calendar;
import mikolaj.project.backendapp.model.Member;
import mikolaj.project.backendapp.model.Trainer;

import java.util.List;

public interface CalendarService {
    ServiceResponse<?> signUpForActivity(Member member, Activity activity);
    ServiceResponse<?> createPersonalTraining(Calendar calendar);
    ServiceResponse<List<Calendar>> getEntriesForMember(Member member);
    ServiceResponse<?> getEntriesForTrainer(Trainer trainer, DateRange dateRange);
    ServiceResponse<?> cancelCalendarEntry(Calendar calendar);
    ServiceResponse<?> checkIfMemberAlreadySigned(Activity activity, Member member);

}
