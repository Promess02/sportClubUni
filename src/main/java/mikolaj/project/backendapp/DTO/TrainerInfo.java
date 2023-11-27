package mikolaj.project.backendapp.DTO;

import lombok.Data;

@Data
public class TrainerInfo {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String profileImageUrl;
    private String specialization;
    private String TeamName;
    private Double grade;

    public TrainerInfo() {
    }
    public TrainerInfo(String name, String surname, String email, String phoneNumber, String profileImageUrl, String specialization, String teamName, Double grade) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profileImageUrl = profileImageUrl;
        this.specialization = specialization;
        TeamName = teamName;
        this.grade = grade;
    }
}
