package mikolaj.project.backendapp.DTO;

import lombok.Data;

@Data
public class NewsPostFilter {
    private Integer locationId;
    private Integer membershipTypeId;
    private Integer activityId;
    private int years;
    private int months;
    private int days;
}
