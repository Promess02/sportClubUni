package mikolaj.project.backendapp.service;


import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.model.CreditCard;
import mikolaj.project.backendapp.model.User;

public interface UserService {
        ServiceResponse<User> checkIfExists(User user);
        ServiceResponse<User> changePassword(String email, String oldPassword, String newPassword);
        ServiceResponse<User> changePhoneNumber(String email, String phoneNumber);
        ServiceResponse<User> addCreditCard(String email, CreditCard creditCard);
        ServiceResponse<User> addProfileImage(String email, String ImageUrl);

}
