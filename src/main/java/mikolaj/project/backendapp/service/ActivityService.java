package mikolaj.project.backendapp.service;

import mikolaj.project.backendapp.DTO.ActivityFilterForm;
import mikolaj.project.backendapp.DTO.DateRange;
import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.model.Activity;

import java.time.LocalDate;

public interface ActivityService{
    //trainer and admin
    ServiceResponse<?> addActivity(Activity activity);
    ServiceResponse<?> updateActivity(Activity activity);
    ServiceResponse<?> deleteActivity(Activity activity);
    // client
    ServiceResponse<?> getFilteredActivities(ActivityFilterForm activityFilterForm);
    ServiceResponse<?> getAllActivities();
    ServiceResponse<?> getAllActivitiesBetweenDates(DateRange dateRange);

    ServiceResponse<?> checkIfActivityAvailable(Activity activity);
}
