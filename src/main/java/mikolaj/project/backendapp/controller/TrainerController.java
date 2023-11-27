package mikolaj.project.backendapp.controller;

import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.DTO.TrainerForm;
import mikolaj.project.backendapp.DTO.TrainerGrade;
import mikolaj.project.backendapp.DTO.TrainerTeam;
import mikolaj.project.backendapp.model.Trainer;
import mikolaj.project.backendapp.model.User;
import mikolaj.project.backendapp.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getTrainers(){
        ServiceResponse<?> response = trainerService.getAllTrainers();
        return ResponseUtil.okResponse(response.getMessage(), "Trainers", response.getData());
    }

    @GetMapping("/getTrainer")
    public ResponseEntity<?> getTrainerProfile(@RequestBody Trainer trainer){
        ServiceResponse<?> response = trainerService.getTrainerInfo(trainer);
        if(response.getData().isEmpty()) return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "Trainer", response.getData());
    }

    @PostMapping("/hireTrainer")
    public ResponseEntity<?> hireTrainer(@RequestBody TrainerForm trainerForm){
        User user = new User(trainerForm.getName(), trainerForm.getSurname(),
                trainerForm.getEmail(), trainerForm.getPassword(), trainerForm.getPhoneNumber());
        Trainer trainer = new Trainer(trainerForm.getSpecialization());
        ServiceResponse<?> response = trainerService.addTrainer(user, trainer);
        if (response.getData().isEmpty()) return ResponseUtil.somethingWentWrongResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "Trainer", response.getData());
    }

    @PatchMapping("/assignTrainerToTeam")
    public ResponseEntity<?> assignTrainerToTeam(@RequestBody TrainerTeam trainerTeam){
        ServiceResponse<?> response = trainerService.assignTrainerToTeam(trainerTeam.getTrainer(),
                trainerTeam.getTeam());
        if(response.getData().isEmpty()) return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "Trainer", response.getData());
    }
    @PatchMapping("/gradeTrainer")
    public ResponseEntity<?> gradeTrainer(@RequestBody TrainerGrade trainerGrade){
        ServiceResponse<?> response = trainerService.gradeTrainer(trainerGrade.getTrainer(), trainerGrade.getGrade(),
                trainerGrade.getMember());
        if(response.getData().isEmpty()) return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "Trainer", response.getData());
    }

    @DeleteMapping("/fireTrainer")
    public ResponseEntity<?> fireTrainer(@RequestBody Trainer trainer){
        ServiceResponse<?> response = trainerService.deleteTrainer(trainer);
        if(response.getMessage().equals("trainer deleted successfully")) return ResponseUtil.okResponse(response.getMessage(),
                "Trainer", response.getData());
        if(response.getMessage().equals("no trainer found")) return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.somethingWentWrongResponse(response.getMessage());
    }

    @PutMapping("/changeTrainer")
    public ResponseEntity<?> changeTrainer(@RequestBody Trainer trainer){
        ServiceResponse<?> response = trainerService.updateTrainer(trainer);
        if(response.getData().isEmpty()) return ResponseUtil.badRequestResponse(response.getMessage());
        return ResponseUtil.okResponse(response.getMessage(), "Trainer", response.getData());
    }
}
