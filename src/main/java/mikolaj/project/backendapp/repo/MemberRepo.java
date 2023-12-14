package mikolaj.project.backendapp.repo;


import mikolaj.project.backendapp.model.Member;
import mikolaj.project.backendapp.model.Team;
import mikolaj.project.backendapp.model.User;

import java.util.List;
import java.util.Optional;

public interface MemberRepo extends RepoTemplate<Member>{
    Optional<Member> findMemberByUserId(Integer userId);
    Optional<Member> findMemberByUser(User user);

    List<Member> findAllByTeam(Team team);
}
