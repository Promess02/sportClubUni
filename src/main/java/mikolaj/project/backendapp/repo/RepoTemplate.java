package mikolaj.project.backendapp.repo;

import java.util.List;
import java.util.Optional;

public interface RepoTemplate<T> {
    List<T> findAll();
    T save(T entity);

    Optional<T> findById(long id);

    boolean existsById(long id);

    long count();

    void deleteById(long id);

    void delete(T entity);

    void deleteAll();

}
