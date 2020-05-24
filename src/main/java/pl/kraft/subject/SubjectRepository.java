package pl.kraft.subject;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
    Optional<Subject> findByName(String name);
    Optional<Subject> findByTerm(Integer term);
    Optional<Subject> findByNameAndTerm(String name, Integer term);
    List<Subject> findAllByName(String name);
    @Query(value = "SELECT * FROM subject GROUP BY name", nativeQuery = true)
    List<Subject> findAllDistinctName();
}
