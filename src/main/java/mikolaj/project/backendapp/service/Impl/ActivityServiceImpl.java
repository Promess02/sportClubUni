package mikolaj.project.backendapp.service.Impl;

import mikolaj.project.backendapp.DTO.ActivityFilterForm;
import mikolaj.project.backendapp.DTO.DateRange;
import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.enums.Sport;
import mikolaj.project.backendapp.model.Activity;
import mikolaj.project.backendapp.model.Location;
import mikolaj.project.backendapp.model.Team;
import mikolaj.project.backendapp.model.Trainer;
import mikolaj.project.backendapp.repo.ActivityRepo;
import mikolaj.project.backendapp.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {
    private ActivityRepo activityRepo;

    @Autowired
    public ActivityServiceImpl(ActivityRepo activityRepo) {
        this.activityRepo = activityRepo;
    }

    @Override
    public ServiceResponse<?> addActivity(Activity activity) {
        activityRepo.save(activity);
        Optional<Activity> activitySaved = activityRepo.findActivityByName(activity.getName());
        if(activitySaved.isEmpty()) return new ServiceResponse<>(Optional.empty(),"saving activity failed");
        return new ServiceResponse<>(activitySaved, "activity saved successfully");
    }

    @Override
    public ServiceResponse<?> updateActivity(Activity activity) {
        if(!activityRepo.existsById(activity.getId())) return new ServiceResponse<>(Optional.empty(), "updated activity not found");
        activityRepo.save(activity);
        Optional<Activity> activitySaved = activityRepo.findActivityByName(activity.getName());
        if(activitySaved.isEmpty()) return new ServiceResponse<>(Optional.empty(),"saving activity failed");
        return new ServiceResponse<>(activitySaved, "activity updated successfully");
    }

    @Override
    public ServiceResponse<?> deleteActivity(Activity activity) {
        if(!activityRepo.existsById(activity.getId())) return new ServiceResponse<>(Optional.empty(), "deleted activity not found");
        activityRepo.delete(activity);
        Optional<Activity> activityDeleted = activityRepo.findActivityByName(activity.getName());
        if(activityDeleted.isPresent()) return new ServiceResponse<>(Optional.empty(), "activity deletion failed");
        return new ServiceResponse<>(Optional.empty(), "activity deleted successfully");
    }

    @Override
    public ServiceResponse<?> getFilteredActivities(ActivityFilterForm activityFilterForm) {
        List<Activity> list = activityRepo.findAll();
        if(list.isEmpty()) return new ServiceResponse<>(Optional.empty(), "no activities found");
        return new ServiceResponse<>(Optional.of(filterActivities(list, activityFilterForm)), "Filtered activities");
    }
    private List<Activity> filterActivities(List<Activity> activities, ActivityFilterForm filterForm) {
        return activities.stream()
                .filter(activity -> filterBySport(activity, Sport.fromValue(filterForm.getSport())))
                .filter(activity -> filterByLocation(activity, filterForm.getLocation()))
                .filter(activity -> filterByTrainer(activity, filterForm.getTrainer()))
                .filter(activity -> filterByTeam(activity, filterForm.getTeam()))
                .collect(Collectors.toList());
    }

    private boolean filterBySport(Activity activity, Sport sport) {
        return sport == null || activity.getSport().equals(sport);
    }

    private boolean filterByLocation(Activity activity, Location location) {
        return location == null || activity.getLocation().equals(location);
    }

    private boolean filterByTrainer(Activity activity, Trainer trainer) {
        return trainer == null || activity.getTrainer().equals(trainer);
    }

    private boolean filterByTeam(Activity activity, Team team) {
        return team == null || activity.getTeam().equals(team);
    }
    @Override
    public ServiceResponse<?> getAllActivities() {
        List<Activity> list = activityRepo.findAll();
        if(list.isEmpty()) return new ServiceResponse<>(Optional.empty(), "no activities found");
        return new ServiceResponse<>(Optional.of(list), "activities retrieved successfully");
    }

    @Override
    public ServiceResponse<?> getAllActivitiesBetweenDates(DateRange dateRange) {
        List<Activity> list = activityRepo.findAll();
        if(list.isEmpty()) return new ServiceResponse<>(Optional.empty(), "no activities found");
        LocalDate startDate = dateRange.getStartDate();
        LocalDate endDate = dateRange.getEndDate();

        List<Activity> filtredList = list.stream().filter(activity -> activity.getDate().isAfter(startDate) && activity.getDate().isBefore(endDate)).toList();

        return new ServiceResponse<>(Optional.of(filtredList), "filtered list by date retrieved successfully");

    }

    @Override
    public ServiceResponse<?> checkIfActivityAvailable(Activity activity) {
        if(!activityRepo.existsById(activity.getId())) return new ServiceResponse<>(Optional.empty(), "activity not found");
        Optional<Activity> activityRetrieved = activityRepo.findById(activity.getId());
        if(activity.getCurrentMembers()< activity.getMemberLimit()) return new ServiceResponse<>(activityRetrieved, "activity available");
        return new ServiceResponse<>(activityRetrieved, "activity not available");
    }
}
