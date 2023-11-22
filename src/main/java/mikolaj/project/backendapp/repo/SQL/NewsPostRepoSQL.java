package mikolaj.project.backendapp.repo.SQL;


import mikolaj.project.backendapp.model.NewsPost;
import mikolaj.project.backendapp.repo.NewsPostRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsPostRepoSQL extends JpaRepository<NewsPost, Long>, NewsPostRepo {
}
