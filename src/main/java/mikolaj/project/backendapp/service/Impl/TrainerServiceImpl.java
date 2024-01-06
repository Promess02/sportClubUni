package mikolaj.project.backendapp.service.Impl;

import mikolaj.project.backendapp.DTO.ServiceResponse;
import mikolaj.project.backendapp.DTO.TrainerInfo;
import mikolaj.project.backendapp.model.*;
import mikolaj.project.backendapp.repo.*;
import mikolaj.project.backendapp.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {
private final UserRepo userRepo;
private final TrainerRepo trainerRepo;
private final TrainerGradeRepo trainerGradeRepo;
private final MemberRepo memberRepo;
private final TeamRepo teamRepo;
private final CalendarRepo calendarRepo;
@Autowired
    public TrainerServiceImpl(CalendarRepo calendarRepo, UserRepo userRepo, TrainerRepo trainerRepo, TrainerGradeRepo trainerGradeRepo, MemberRepo memberRepo, TeamRepo teamRepo) {
        this.userRepo = userRepo;
        this.trainerRepo = trainerRepo;
        this.trainerGradeRepo = trainerGradeRepo;
    this.memberRepo = memberRepo;
    this.teamRepo = teamRepo;
    this.calendarRepo = calendarRepo;
}
    @Override
    public ServiceResponse<?> addTrainer(User user, Trainer trainer) {
        Optional<User> userDb = userRepo.findById(user.getId());
        if(userDb.isEmpty()) {
            //add user if he isn't already in db
            userRepo.save(user);
            userDb = userRepo.findByEmailIgnoreCase(user.getEmail());
            if(userDb.isEmpty()) return new ServiceResponse<>(Optional.empty(), "failed saving user");
        }
        trainer.setUser(userDb.get());
        trainer.setGrade(0d);
        trainer.setNumOfGrades(0);
        trainer.setSumOfGrades(0);
        trainerRepo.save(trainer);
        return new ServiceResponse<Object>(Optional.of(trainer), "successfully added a trainer");
    }


    @Override
    public ServiceResponse<?> getTrainerInfo(Trainer trainer) {
        Optional<Trainer> trainerDb = trainerRepo.findById(trainer.getId());
        if(trainerDb.isEmpty()) return new ServiceResponse<>(Optional.empty(), "couldn't find trainer");
        User user = trainer.getUser();
        TrainerInfo trainerInfo = new TrainerInfo(user.getName(), user.getSurname(),
                user.getEmail(), user.getPhoneNumber(), user.getProfileImageUrl(), trainer.getSpecialization(),
                trainer.getTeam().getName(), trainer.getGrade());
        return new ServiceResponse<>(Optional.of(trainerInfo), "successfully received trainer info");
    }

    @Override
    public ServiceResponse<?> getAllTrainers() {
        List<Trainer> trainerList = trainerRepo.findAll();
        if(trainerList.isEmpty()) return new ServiceResponse<>(Optional.empty(), "no trainers found");
        return new ServiceResponse<>(Optional.of(trainerList), "trainers successfully retrieved");
    }

    @Override
    public ServiceResponse<?> assignTrainerToTeam(Trainer trainer, Team team) {
        Optional<Trainer> trainerDb = trainerRepo.findById(trainer.getId());
        if(trainerDb.isEmpty()) return new ServiceResponse<Object>(Optional.empty(), "no trainer found");
        Optional<Team> teamDb = teamRepo.findById(team.getId());
        if(teamDb.isEmpty()) return new ServiceResponse<Object>(Optional.empty(), "no team found");
        Trainer trainerUpdated = trainerDb.get();
        trainerUpdated.setTeam(team);
        trainerRepo.save(trainerUpdated);
        return new ServiceResponse<>(Optional.of(trainerUpdated), "team successfully assigned to trainer");
    }

    @Override
    public ServiceResponse<?> gradeTrainer(Trainer trainer, Byte grade, Member member) {
        Optional<Trainer> trainerDb = trainerRepo.findById(trainer.getId());
        if(trainerDb.isEmpty()) return new ServiceResponse<>(Optional.empty(), "no trainer found");
        Trainer trainerUpdated = trainerDb.get();
        Optional<Member> memberDb = memberRepo.findById(member.getId());
        if(memberDb.isEmpty()) return new ServiceResponse<>(Optional.empty(), "no member found");
        Optional<TrainerGrade> trainerGrade = trainerGradeRepo.findTrainerGradeByMemberAndTrainer(memberDb.get(), trainerUpdated);
        if(trainerGrade.isEmpty()){
            trainerUpdated.addGrade(grade);
            trainerGradeRepo.save(new TrainerGrade(member, trainer, grade));
        }
        else{
            trainerUpdated.changeGrade(trainerGrade.get().getGrade(), grade);
            trainerGradeRepo.delete(trainerGrade.get());
            trainerGradeRepo.save(new TrainerGrade(member,trainer,grade));
        }
        trainerRepo.save(trainerUpdated);
        return new ServiceResponse<>(Optional.empty(), "trainer graded successfully");
    }

    @Override
    public ServiceResponse<?> deleteTrainer(Trainer trainer) {
        Optional<Trainer> trainerDb = trainerRepo.findById(trainer.getId());
        if(trainerDb.isEmpty()) return new ServiceResponse<>(Optional.empty(), "no trainer found");
        trainerRepo.delete(trainer);
        trainerDb = trainerRepo.findById(trainer.getId());
        if(trainerDb.isEmpty()) return new ServiceResponse<>(Optional.empty(), "trainer deleted successfully");
        return new ServiceResponse<>(trainerDb, "trainer deletion failed");
    }

    @Override
    public ServiceResponse<?> updateTrainer(Trainer trainer) {
        Optional<Trainer> trainerDb = trainerRepo.findById(trainer.getId());
        if(trainerDb.isEmpty()) return new ServiceResponse<>(Optional.empty(), "no trainer found");
        trainerRepo.save(trainer);
        trainerDb = trainerRepo.findById(trainer.getId());
        return new ServiceResponse<>(trainerDb, "trainer updated successfully");
    }

    @Override
    public ServiceResponse<List<Calendar>> getTrainerCalendar(Trainer trainer, LocalDate date) {
        List<Calendar> calendarList = calendarRepo.findCalendarsByTrainerAndDate(trainer,date);
        return new ServiceResponse<>(Optional.of(calendarList), "successfully retrieved calendars for date");
    }
}
