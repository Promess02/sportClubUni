package mikolaj.project.backendapp.DTO;

import lombok.Data;
import mikolaj.project.backendapp.model.Activity;
import mikolaj.project.backendapp.model.Member;

@Data
public class MemberActivity {
    private Member member;
    private Activity activity;
}
