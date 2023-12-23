package mikolaj.project.backendapp.repo.SQL;

import mikolaj.project.backendapp.model.Address;
import mikolaj.project.backendapp.repo.AddressRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepoSQL extends JpaRepository<Address, Long>, AddressRepo {
}
