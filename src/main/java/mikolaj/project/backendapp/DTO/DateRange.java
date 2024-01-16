package mikolaj.project.backendapp.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DateRange {
    private LocalDate startDate;
    private LocalDate endDate;
    public DateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
