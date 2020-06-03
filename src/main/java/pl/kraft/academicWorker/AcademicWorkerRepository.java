package pl.kraft.academicWorker;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AcademicWorkerRepository extends PagingAndSortingRepository<AcademicWorker, Long> {
    Optional<AcademicWorker> findByEmail(String email);
    Optional<AcademicWorker> findByLogin(String login);
    Page<AcademicWorker> findAllBySurnameContainingIgnoreCaseOrNameContainingIgnoreCase(String surname, String name, Pageable pageable);
}
