package pl.kraft.student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    Optional<Student> findByLogin(String login);
    Page<Student> findAllBySurnameContainingIgnoreCaseOrNameContainingIgnoreCase(String surname, String name, Pageable pageable);
}
