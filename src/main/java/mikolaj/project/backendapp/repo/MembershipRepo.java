package mikolaj.project.backendapp.repo;


import mikolaj.project.backendapp.model.Membership;

import java.util.Optional;
import java.util.List;

public interface MembershipRepo extends RepoTemplate<Membership>{
    Optional<Membership> findMembershipByMemberIdAndMembershipStatusIsTrue(Integer memberId);
    Optional<List<Membership>> findMembershipsByMemberId(Integer memberId);
}
