package mikolaj.project.backendapp.service;


import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.model.Membership;

public interface MembershipService {
    ServiceResponse<?> getAllMembershipTypes();
    ServiceResponse<Membership> getMembershipForUser(String userEmail);
    ServiceResponse<?> buyMembership(String userEmail, String membershipDescription, String activityName);
    ServiceResponse<?> getUserMembershipHistory(String userEmail);
}
