package mikolaj.project.backendapp.repo.SQL;


import mikolaj.project.backendapp.model.CreditCard;
import mikolaj.project.backendapp.repo.CreditCardRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepoSQL extends JpaRepository<CreditCard, Long>, CreditCardRepo {
    @Override
    boolean existsCreditCardByNumber(Long number);
}
