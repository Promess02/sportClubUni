package mikolaj.project.backendapp.repo;


import mikolaj.project.backendapp.enums.MembershipStatus;
import mikolaj.project.backendapp.model.Membership;

import java.util.List;
import java.util.Optional;

public interface MembershipRepo extends RepoTemplate<Membership>{
    Optional<Membership> findMembershipByMemberIdAndMembershipStatusEquals(Integer memberId, MembershipStatus membershipStatus);
    Optional<List<Membership>> findMembershipsByMemberId(Integer memberId);
}
