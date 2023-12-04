package mikolaj.project.backendapp.repo;


import mikolaj.project.backendapp.enums.MembershipStatus;
import mikolaj.project.backendapp.model.Membership;

import java.util.Optional;
import java.util.List;

public interface MembershipRepo extends RepoTemplate<Membership>{
    Optional<Membership> findMembershipByMemberIdAndMembershipStatusEquals(Integer memberId, MembershipStatus membershipStatus);
    Optional<List<Membership>> findMembershipsByMemberId(Integer memberId);
}
