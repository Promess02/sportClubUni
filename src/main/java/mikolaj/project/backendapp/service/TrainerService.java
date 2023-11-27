package mikolaj.project.backendapp.service;

import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.model.Member;
import mikolaj.project.backendapp.model.Team;
import mikolaj.project.backendapp.model.Trainer;
import mikolaj.project.backendapp.model.User;

public interface TrainerService {
    ServiceResponse<?> addTrainer(User user, Trainer trainer);
    ServiceResponse<?> getTrainerInfo(Trainer trainer);
    ServiceResponse<?> getAllTrainers();
    ServiceResponse<?> assignTrainerToTeam(Trainer trainer, Team team);
    ServiceResponse<?> gradeTrainer(Trainer trainer, Byte grade, Member member);
    ServiceResponse<?> deleteTrainer(Trainer trainer);
    ServiceResponse<?> updateTrainer(Trainer trainer);
}
