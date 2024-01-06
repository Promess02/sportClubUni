package mikolaj.project.backendapp.service.Impl;

import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.model.CreditCard;
import mikolaj.project.backendapp.model.User;
import mikolaj.project.backendapp.repo.CreditCardRepo;
import mikolaj.project.backendapp.repo.UserRepo;
import mikolaj.project.backendapp.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    private final UserRepo userRepo;
    private final CreditCardRepo creditCardRepo;

    @Autowired
    public CreditCardServiceImpl(UserRepo userRepo, CreditCardRepo creditCardRepo) {
        this.userRepo = userRepo;
        this.creditCardRepo = creditCardRepo;
    }

    @Override
    public ServiceResponse<CreditCard> saveCreditCardForUser(CreditCard creditCard, User user) {
        user.setCreditCard(creditCard);
        creditCardRepo.save(creditCard);
        userRepo.save(user);
        return new ServiceResponse<>(Optional.of(creditCard), "credit card saved");
    }

    @Override
    public ServiceResponse<CreditCard> getCreditCardForUser(User user) {
        User userDb = userRepo.findByEmailIgnoreCase(user.getEmail()).orElse(null);
        if(userDb == null) return new ServiceResponse<>(Optional.empty(), "user not found in the db");
        if(userDb.getCreditCard()==null) return new ServiceResponse<>(Optional.empty(), "user doesn't have a credit card");
        return new ServiceResponse<>(Optional.of(user.getCreditCard()), "user credit card retrieved");
    }
}
