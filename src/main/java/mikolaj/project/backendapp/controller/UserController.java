package mikolaj.project.backendapp.controller;


import mikolaj.project.backendapp.DTO.CreditCardForm;
import mikolaj.project.backendapp.DTO.PasswordForm;
import mikolaj.project.backendapp.DTO.ProfileImageForm;
import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.model.CreditCard;
import mikolaj.project.backendapp.model.User;
import mikolaj.project.backendapp.service.Impl.ServiceMessages;
import mikolaj.project.backendapp.service.RegistrationService;
import mikolaj.project.backendapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final RegistrationService registrationService;
    private final UserService userService;

    @Autowired
    public UserController(RegistrationService registrationService, UserService userService) {
        this.registrationService = registrationService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        ServiceResponse<User> response = registrationService.saveUser(user);
        return ResponseUtil.okResponse(response.getMessage(), "User", response.getData());
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        ServiceResponse<User> response = registrationService.confirmEmail(confirmationToken);
        return ResponseUtil.okResponse(response.getMessage(),"User", response.getData());
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user){
        ServiceResponse<?> response = userService.checkIfExists(user);
        if(!response.getMessage().equals(ServiceMessages.ACCOUNT_EXISTS))
            return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(),"User", response.getData());
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody PasswordForm passwordForm){
       ServiceResponse<?> response = userService.changePassword(passwordForm.getEmail(),
               passwordForm.getOldPassword(), passwordForm.getNewPassword());
        if(!response.getMessage().equals(ServiceMessages.ACCOUNT_UPDATED))
            return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse("Password successfully changed", "User", response.getData());
    }

    @PutMapping("/addCreditCard")
    public ResponseEntity<?> addCreditCard(@RequestBody CreditCardForm creditCardForm){
        String email = creditCardForm.getEmail();
        CreditCard creditCard = creditCardForm.getCreditCard();
        ServiceResponse<?> response = userService.addCreditCard(email,creditCard);
        if(!response.getMessage().equals(ServiceMessages.ACCOUNT_UPDATED))
            return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse("Credit card added successfully", "User", response.getData());
    }

    @PutMapping("/addProfileImage")
    public ResponseEntity<?> addProfileImage(@RequestBody ProfileImageForm profileImageForm){
        ServiceResponse<User> response = userService.addProfileImage(profileImageForm.getEmail(),
                profileImageForm.getImageUrl());
        if(response.getMessage().equals(ServiceMessages.EMAIL_NOT_FOUND))
            return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse("image added successfully", "User", response.getData());
    }

}
