package mikolaj.project.backendapp.service;


import mikolaj.project.backendapp.DTO.ServiceResponse;

public interface MembershipService {
    ServiceResponse<?> getAllMembershipTypes();
    ServiceResponse<?> getMembershipForUser(String userEmail);
    ServiceResponse<?> buyMembership(String userEmail, String membershipDescription, String activityName);
    ServiceResponse<?> getUserMembershipHistory(String userEmail);
}
