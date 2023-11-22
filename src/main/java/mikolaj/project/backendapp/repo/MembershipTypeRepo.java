package mikolaj.project.backendapp.repo;


import mikolaj.project.backendapp.model.MembershipType;

import java.util.Optional;

public interface MembershipTypeRepo extends RepoTemplate<MembershipType>{
    Optional<MembershipType> findMembershipTypeByDescription(String description);
}
