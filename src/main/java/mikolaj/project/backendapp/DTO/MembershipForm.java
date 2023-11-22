package mikolaj.project.backendapp.DTO;

import lombok.Data;

@Data
public class MembershipForm {
    private String userEmail;
    private String membershipDesc;
    private String activityName;
}
