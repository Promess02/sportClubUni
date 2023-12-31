package mikolaj.project.backendapp.repo.SQL;


import mikolaj.project.backendapp.enums.MembershipStatus;
import mikolaj.project.backendapp.model.Membership;
import mikolaj.project.backendapp.repo.MembershipRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembershipRepoSQL extends JpaRepository<Membership, Long>, MembershipRepo {
    @Override
    Optional<Membership> findMembershipByMemberIdAndMembershipStatusEquals(Integer member_id, MembershipStatus membershipStatus);

    @Override
    Optional<List<Membership>> findMembershipsByMemberId(Integer memberId);
}

