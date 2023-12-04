package mikolaj.project.backendapp.controller;

import mikolaj.project.backendapp.DTO.EntriesRequest;
import mikolaj.project.backendapp.DTO.MemberActivity;
import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.model.Calendar;
import mikolaj.project.backendapp.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calendar")
public class CalendarController {
    private final CalendarService calendarService;
    @Autowired
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping("/signUpForActivity")
    public ResponseEntity<?> signUpForActivity(@RequestBody MemberActivity memberActivity){
        ServiceResponse<?> response = calendarService.signUpForActivity(memberActivity.getMember(), memberActivity.getActivity());
        if(response.getData().isEmpty()) return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "Calendar", response.getData());
    }

    @PostMapping("/createPersonalTraining")
    public ResponseEntity<?> createPersonalTraining(@RequestBody Calendar calendar){
        ServiceResponse<?> response = calendarService.createPersonalTraining(calendar);
        if(response.getData().isEmpty()) return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "Calendar", response.getData());
    }

    @GetMapping("/getCalendarForMember")
    public ResponseEntity<?> getCalendarForMember(@RequestBody EntriesRequest entriesRequest){
        ServiceResponse<?> response = calendarService.getEntriesForMember(entriesRequest.getMember());
        if(response.getData().isEmpty()) return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "Calendar", response.getData());
    }
    @GetMapping
    public ResponseEntity<?> getCalendarForTrainer(@RequestBody EntriesRequest entriesRequest){
        ServiceResponse<?> response = calendarService.getEntriesForTrainer(entriesRequest.getTrainer(), entriesRequest.getDateRange());
        if(response.getData().isEmpty()) return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "Calendar", response.getData());
    }

    @DeleteMapping
    public ResponseEntity<?> cancelCalendar(@RequestBody Calendar calendar){
        ServiceResponse<?> response = calendarService.cancelCalendarEntry(calendar);
        if(response.getData().isEmpty()) return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "Calendar", response.getData());
    }
}
