package mikolaj.project.backendapp.service.Impl;

import mikolaj.project.backendapp.DTO.DateRange;
import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.model.Activity;
import mikolaj.project.backendapp.model.Calendar;
import mikolaj.project.backendapp.model.Member;
import mikolaj.project.backendapp.model.Trainer;
import mikolaj.project.backendapp.repo.ActivityRepo;
import mikolaj.project.backendapp.repo.CalendarRepo;
import mikolaj.project.backendapp.repo.MemberRepo;
import mikolaj.project.backendapp.repo.TrainerRepo;
import mikolaj.project.backendapp.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CalendarServiceImpl implements CalendarService {
    private ActivityRepo activityRepo;
    private CalendarRepo calendarRepo;
    private MemberRepo memberRepo;
    private TrainerRepo trainerRepo;
    @Autowired
    public CalendarServiceImpl(ActivityRepo activityRepo, CalendarRepo calendarRepo, MemberRepo memberRepo, TrainerRepo trainerRepo) {
        this.activityRepo = activityRepo;
        this.calendarRepo = calendarRepo;
        this.memberRepo = memberRepo;
        this.trainerRepo = trainerRepo;
    }

    @Override
    public ServiceResponse<?> signUpForActivity(Member member, Activity activity) {
        Optional<Member> memberDb = memberRepo.findById(member.getId());
        if(memberDb.isEmpty()) return new ServiceResponse<>(Optional.empty(), "no member found");
        Optional<Activity> activityDb = activityRepo.findById(activity.getId());
        if(activityDb.isEmpty()) return new ServiceResponse<>(Optional.empty(),"no activity found");
        activity.signUp();
        activityRepo.save(activity);
        Calendar calendar = new Calendar(activity.getName(), activity.getDate(), activity.getTime(),
                activity.getMinutes(), activity.getDescription(), member, activity.getTrainer(), activity);
        calendarRepo.save(calendar);
        return new ServiceResponse<>(Optional.of(calendar), "calendar entry successfully added");
    }

    @Override
    public ServiceResponse<?> createPersonalTraining(Calendar calendar) {
        List<Calendar> trainerCalendar = calendarRepo.findCalendarsByTrainer(calendar.getTrainer());
        List<Calendar> checkIfFree = trainerCalendar.stream().filter(calendarDb ->
                calendar.getDate().equals(calendarDb.getDate()) &&
                calendar.getTime().isAfter(calendarDb.getTime()) &&
                calendar.getTime().isBefore(calendarDb.getTime().minusMinutes(calendar.getMinutes()))).toList();
        if(!checkIfFree.isEmpty()) return new ServiceResponse<>(Optional.empty(), "trainer is busy at this time");
        calendarRepo.save(calendar);
        return new ServiceResponse<>(Optional.of(calendar), "personal training saved");
    }

    @Override
    public ServiceResponse<?> getEntriesForMember(Member member, DateRange dateRange) {
        List<Calendar> entryList = calendarRepo.findAll();
        if(entryList.isEmpty()) return new ServiceResponse<>(Optional.empty(),"no entries found");
        List<Calendar> listForMember = entryList.stream().filter(calendar ->
                calendar.getMember().equals(member) &&
                calendar.getDate().isAfter(dateRange.getStartDate()) &&
                calendar.getDate().isBefore(dateRange.getEndDate())).toList();
        return new ServiceResponse<>(Optional.of(listForMember), "found entries for member");

    }

    @Override
    public ServiceResponse<?> getEntriesForTrainer(Trainer trainer, DateRange dateRange) {
        List<Calendar> entryList = calendarRepo.findAll();
        if (entryList.isEmpty()) {
            return new ServiceResponse<>(Optional.empty(), "No entries found");
        }

        Set<Activity> uniqueActivities = new HashSet<>();

        List<Calendar> listForTrainer = entryList.stream()
                .filter(calendar -> calendar.getTrainer().equals(trainer) &&
                        calendar.getDate().isAfter(dateRange.getStartDate()) &&
                        calendar.getDate().isBefore(dateRange.getEndDate()))
                .filter(calendar -> uniqueActivities.add(calendar.getActivity())) // Add activity to set
                .toList();

        return new ServiceResponse<>(Optional.of(listForTrainer), "Found entries for trainer");
    }


    @Override
    public ServiceResponse<?> cancelCalendarEntry(Calendar calendar) {
        Optional<Calendar> calendarDb = calendarRepo.findById(calendar.getId());
        if(calendarDb.isEmpty()) return new ServiceResponse<>(Optional.empty(), "calendar entry not found");
        if(calendar.getActivity()!=null) {
            calendar.getActivity().cancel();
            activityRepo.save(calendar.getActivity());
        }
        calendarRepo.delete(calendar);
        calendarDb = calendarRepo.findById(calendar.getId());
        if (calendarDb.isEmpty()) return new ServiceResponse<>(Optional.of(calendar), "entry cancelled successfully");
        return new ServiceResponse<>(Optional.empty(), "failed deleting calendar entry");
    }
}
