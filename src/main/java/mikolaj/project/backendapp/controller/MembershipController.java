package mikolaj.project.backendapp.controller;

import lombok.RequiredArgsConstructor;

import mikolaj.project.backendapp.DTO.EmailWrapper;
import mikolaj.project.backendapp.DTO.MembershipForm;
import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/membership")
public class MembershipController {

    private final MembershipService membershipService;

    @Autowired
    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @GetMapping("/getMembershipTypes")
    public ResponseEntity<?>  getMembershipTypes(){
        ServiceResponse<?> response = membershipService.getAllMembershipTypes();
        if(response.getMessage().equals("No membership types found")) return ResponseUtil.badRequestResponse(
                response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "MembershipTypes", response.getData());
    }

    @GetMapping("/getMembership")
    public ResponseEntity<?> getMembershipsForUser(@RequestBody EmailWrapper wrapper){
        String userEmail = wrapper.getEmail();
        ServiceResponse<?> response = membershipService.getMembershipForUser(userEmail);
        if(!response.getMessage().equals("Membership found"))
            return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "Membership", response.getData());
    }

    @PostMapping("/buyMembership")
    public ResponseEntity<?> buyMembership(@RequestBody MembershipForm membershipForm){
        String email = membershipForm.getUserEmail();
        String membershipDesc = membershipForm.getMembershipDesc();
        String activityName = membershipForm.getActivityName();
        ServiceResponse<?> response = membershipService.buyMembership(email,membershipDesc,activityName);
        if(!response.getMessage().equals("Membership successfully bought"))
            return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "Membership", response.getData());
    }

    @GetMapping("/getHistory")
    public ResponseEntity<?> getUserMembershipHistory(@RequestBody EmailWrapper wrapper){
        String userEmail = wrapper.getEmail();
        ServiceResponse<?> response = membershipService.getUserMembershipHistory(userEmail);
        if(!response.getMessage().equals("membership history retrieved for user"))
            return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(),"Memberships", response.getData());
    }

}
