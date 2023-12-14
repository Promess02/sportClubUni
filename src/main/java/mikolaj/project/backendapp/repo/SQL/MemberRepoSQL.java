package mikolaj.project.backendapp.repo.SQL;


import mikolaj.project.backendapp.model.Member;
import mikolaj.project.backendapp.model.Team;
import mikolaj.project.backendapp.model.User;
import mikolaj.project.backendapp.repo.MemberRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MemberRepoSQL extends JpaRepository<Member, Long>, MemberRepo {
    @Override
    Optional<Member> findMemberByUserId(Integer userId);

    @Override
    Optional<Member> findMemberByUser(User user);

    @Override
    List<Member> findAllByTeam(Team team);
}
