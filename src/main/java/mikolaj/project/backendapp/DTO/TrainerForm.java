package mikolaj.project.backendapp.DTO;

import lombok.Data;

@Data
public class TrainerForm {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private String specialization;
}
