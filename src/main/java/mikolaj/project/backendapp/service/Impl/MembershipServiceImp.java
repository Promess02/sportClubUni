package mikolaj.project.backendapp.service.Impl;

import lombok.NoArgsConstructor;
import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.enums.MembershipStatus;
import mikolaj.project.backendapp.model.*;
import mikolaj.project.backendapp.repo.*;
import mikolaj.project.backendapp.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class MembershipServiceImp implements MembershipService {
    private MembershipRepo membershipRepo;
    private MemberRepo memberRepo;
    private UserRepo userRepo;
    private MembershipTypeRepo membershipTypeRepo;
    private ActivityRepo activityRepo;

    @Autowired
    public MembershipServiceImp(MembershipRepo membershipRepo, MemberRepo memberRepo, UserRepo userRepo, MembershipTypeRepo membershipTypeRepo, ActivityRepo activityRepo) {
        this.membershipRepo = membershipRepo;
        this.memberRepo = memberRepo;
        this.userRepo = userRepo;
        this.membershipTypeRepo = membershipTypeRepo;
        this.activityRepo = activityRepo;
    }

    @Override
    public ServiceResponse<?> getAllMembershipTypes() {
        List<MembershipType> membershipTypeList = membershipTypeRepo.findAll();
        if(membershipTypeList.isEmpty()) return new ServiceResponse<>(Optional.empty(), "No membership types found");
        return new ServiceResponse<>(Optional.of(membershipTypeList), "Found membership types");
    }

    @Override
    public ServiceResponse<?> getMembershipForUser(String userEmail) {
        ServiceResponse<?> response = checkIfUserEmailOk(userEmail);
        if(!response.getMessage().equals("email OK")) return response;
        User userDb = userRepo.findByEmailIgnoreCase(userEmail);
        Optional<Member> member = memberRepo.findMemberByUserId(userDb.getId());
        if(member.isEmpty()) return new ServiceResponse<>(Optional.empty(), "user with given email has never bought a membership");
        Optional<Membership> membership = membershipRepo.findMembershipByMemberIdAndMembershipStatusIsTrue(member.get().getId());
        if(membership.isEmpty()) return new ServiceResponse<>(Optional.empty(),"member has no active membership");
        return new ServiceResponse<>(membership, "Membership found");
    }

    @Override
    public ServiceResponse<?> buyMembership(String userEmail, String membershipDescription, String activityName) {
        ServiceResponse<?> response = checkIfUserEmailOk(userEmail);
        if(!response.getMessage().equals("email OK")) return response;
        User userDb = userRepo.findByEmailIgnoreCase(userEmail);
        Optional<Member> member = memberRepo.findMemberByUserId(userDb.getId());
        if(member.isEmpty()){
            Member newMember = new Member(userDb);
            memberRepo.save(newMember);
            member = Optional.of(newMember);
        }
        Optional<Membership> membership = membershipRepo.findMembershipByMemberIdAndMembershipStatusIsTrue(member.get().getId());
        if(membership.isPresent()) return new ServiceResponse<>(Optional.empty(),"member has an active membership already");
        Optional<MembershipType> membershipType = membershipTypeRepo.findMembershipTypeByDescription(membershipDescription);
        if(membershipType.isEmpty()) return new ServiceResponse<>(Optional.empty(), "No membership type found with given description");
        Optional<Activity> activity = activityRepo.findActivityByName(activityName);
        if(membershipType.get().getFullAccess().equals(false) && activity.isEmpty()) return new ServiceResponse<>(Optional.empty(),
                "given not full access membership type with no activity provided");

        LocalDate membershipEndDate = LocalDate.now().plusMonths(membershipType.get().getMonths());
        Membership newMembership = new Membership(membershipEndDate, MembershipStatus.ACTIVE,member.get(),membershipType.get(),
                activity.orElse(null));
        membershipRepo.save(newMembership);
        return new ServiceResponse<>(Optional.of(newMembership), "Membership successfully bought");
    }

    @Override
    public ServiceResponse<?> getUserMembershipHistory(String userEmail) {
        ServiceResponse<?> response = checkIfUserEmailOk(userEmail);
        if(!response.getMessage().equals("email OK")) return response;
        User userDb = userRepo.findByEmailIgnoreCase(userEmail);
        Optional<Member> member = memberRepo.findMemberByUserId(userDb.getId());
        if(member.isEmpty()) return new ServiceResponse<>(Optional.empty(), "user with given email has never bought a membership");
        Optional<List<Membership>> membershipList = membershipRepo.findMembershipsByMemberId(member.get().getId());
        if(membershipList.isEmpty()) return new ServiceResponse<>(Optional.empty(), "no memberships found for user");
        return new ServiceResponse<>(Optional.of(membershipList), "membership history retrieved for user");
    }

    private ServiceResponse<?> checkIfUserEmailOk(String userEmail){
        if(userEmail==null) return new ServiceResponse<>(Optional.empty(), ServiceMessages.EMAIL_NOT_GIVEN);
        User userDb = userRepo.findByEmailIgnoreCase(userEmail);
        if(userDb== null) return new ServiceResponse<>(Optional.empty(), ServiceMessages.EMAIL_NOT_FOUND);
        return new ServiceResponse<>(Optional.empty(), "email OK");
    }

}
