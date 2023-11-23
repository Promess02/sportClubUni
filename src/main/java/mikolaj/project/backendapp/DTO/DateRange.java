package mikolaj.project.backendapp.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DateRange {
    private LocalDate startDate;
    private LocalDate endDate;
}
