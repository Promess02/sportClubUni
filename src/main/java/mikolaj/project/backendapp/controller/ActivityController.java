package mikolaj.project.backendapp.controller;

import mikolaj.project.backendapp.DTO.ActivityFilterForm;
import mikolaj.project.backendapp.DTO.DateRange;
import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.model.Activity;
import mikolaj.project.backendapp.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/activity")
public class ActivityController {
private final ActivityService activityService;
@Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getActivities(){
        ServiceResponse<?> response = activityService.getAllActivities();
        if(response.getData().isEmpty()) return ResponseUtil.okResponse("no activities found", "Activity", Optional.empty());
        return ResponseUtil.okResponse(response.getMessage(), "Activities", response.getData());
    }

    @GetMapping("/getBetweenDates")
    public ResponseEntity<?> getActivitiesBetweenDates(@RequestBody DateRange dateRange){
        ServiceResponse<?> response = activityService.getAllActivitiesBetweenDates(dateRange);
        if(response.getData().isEmpty()) return ResponseUtil.okResponse("no activities found", "Actiivies", response.getData());
        return ResponseUtil.okResponse("activities found", "Activities", response.getData());
    }

    @GetMapping("/getFiltered")
    public ResponseEntity<?> getFilteredActivities(@RequestBody ActivityFilterForm activityFilterForm){
        ServiceResponse<?> response = activityService.getFilteredActivities(activityFilterForm);
        if(response.getData().isEmpty()) return ResponseUtil.okResponse("no activities found", "Activities", response.getData());
        return ResponseUtil.okResponse("activities found", "Activities", response.getData());
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkIfAvailable(@RequestBody Activity activity){
    ServiceResponse<?> response = activityService.checkIfActivityAvailable(activity);
    if(response.getData().isEmpty()) return ResponseUtil.badRequestResponse(response.getMessage());
    return ResponseUtil.okResponse(response.getMessage(), "Activity", response.getData());
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveActivity(@RequestBody Activity activity){
        ServiceResponse<?> response = activityService.addActivity(activity);
        if(response.getData().isEmpty()) return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "Activity", response.getData());
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateActivity(@RequestBody Activity activity){
        ServiceResponse<?> response = activityService.updateActivity(activity);
        if(response.getData().isEmpty()) return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "Activity", response.getData());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteActivity(@RequestBody Activity activity){
        ServiceResponse<?> response = activityService.updateActivity(activity);
        if(response.getData().isEmpty()) return ResponseUtil.okResponse(response.getMessage(), "Activity", response.getData());
        return ResponseUtil.badRequestResponse(response.getMessage());
    }
}
