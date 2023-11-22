package mikolaj.project.backendapp.service;


import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.model.User;

public interface RegistrationService {
    ServiceResponse<User> saveUser(User user);

    ServiceResponse<User> confirmEmail(String confirmationToken);


}
