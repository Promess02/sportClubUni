package mikolaj.project.backendapp.repo.SQL;


import mikolaj.project.backendapp.model.SocialMedia;
import mikolaj.project.backendapp.repo.SocialMediaRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialMediaRepoSQL extends JpaRepository<SocialMedia, Long>, SocialMediaRepo {
}
