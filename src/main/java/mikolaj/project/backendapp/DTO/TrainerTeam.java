package mikolaj.project.backendapp.DTO;

import lombok.Data;
import mikolaj.project.backendapp.model.Team;
import mikolaj.project.backendapp.model.Trainer;

@Data
public class TrainerTeam {
    private Trainer trainer;
    private Team team;
}
