package mikolaj.project.backendapp.repo;


import mikolaj.project.backendapp.model.CreditCard;

public interface CreditCardRepo extends RepoTemplate<CreditCard>{
    boolean existsCreditCardByNumber(Long number);
}
