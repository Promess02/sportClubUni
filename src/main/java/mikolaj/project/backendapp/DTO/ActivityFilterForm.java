package mikolaj.project.backendapp.DTO;

import lombok.Data;
import mikolaj.project.backendapp.model.Location;
import mikolaj.project.backendapp.model.Team;
import mikolaj.project.backendapp.model.Trainer;

@Data
public class ActivityFilterForm {
    private String sport;
    private Location location;
    private Trainer trainer;
    private Team team;
}
