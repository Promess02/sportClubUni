package mikolaj.project.backendapp.service;

import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.model.CreditCard;
import mikolaj.project.backendapp.model.User;

public interface CreditCardService {
    ServiceResponse<CreditCard> saveCreditCardForUser(CreditCard creditCard, User user);
    ServiceResponse<CreditCard> getCreditCardForUser(User user);

}
