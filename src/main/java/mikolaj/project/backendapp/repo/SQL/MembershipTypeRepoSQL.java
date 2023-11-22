package mikolaj.project.backendapp.repo.SQL;

import mikolaj.project.backendapp.model.MembershipType;
import mikolaj.project.backendapp.repo.MembershipTypeRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipTypeRepoSQL extends JpaRepository<MembershipType, Long>, MembershipTypeRepo {
    @Override
    Optional<MembershipType> findMembershipTypeByDescription(String description);

}
