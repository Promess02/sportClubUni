package mikolaj.project.backendapp.repo.SQL;


import mikolaj.project.backendapp.model.Member;
import mikolaj.project.backendapp.repo.MemberRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepoSQL extends JpaRepository<Member, Long>, MemberRepo {
    @Override
    Optional<Member> findMemberByUserId(Integer userId);
}
