package mikolaj.project.backendapp.service;

import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.enums.Sport;
import mikolaj.project.backendapp.model.*;

import java.time.LocalDate;
import java.util.List;

public interface TrainerService {
    ServiceResponse<?> addTrainer(User user, Sport sport, Team team);
    ServiceResponse<?> getTrainerInfo(Trainer trainer);
    ServiceResponse<?> getAllTrainers();
    ServiceResponse<?> assignTrainerToTeam(Trainer trainer, Team team);
    ServiceResponse<?> gradeTrainer(Trainer trainer, Byte grade, Member member);
    ServiceResponse<?> deleteTrainer(Trainer trainer);
    ServiceResponse<?> updateTrainer(Trainer trainer);

    ServiceResponse<List<Calendar>> getTrainerCalendar(Trainer trainer, LocalDate date);
}
