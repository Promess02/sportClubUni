package mikolaj.project.backendapp.DTO;

import lombok.Data;
import mikolaj.project.backendapp.model.Member;
import mikolaj.project.backendapp.model.Trainer;

@Data
public class EntriesRequest {
    private Trainer trainer;
    private Member member;
    private DateRange dateRange;
}
