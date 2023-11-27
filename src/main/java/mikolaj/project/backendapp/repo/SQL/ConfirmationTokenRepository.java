package mikolaj.project.backendapp.repo.SQL;

import mikolaj.project.backendapp.model.ConfirmationToken;
import mikolaj.project.backendapp.repo.ConfirmationTokenRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long>, ConfirmationTokenRepo {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}