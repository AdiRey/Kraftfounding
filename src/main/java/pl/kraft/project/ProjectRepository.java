package pl.kraft.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.kraft.ability.Ability;

import java.util.List;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
    Page<Project> findAllByTitleContainingIgnoreCaseAndCompletedIsFalse(String title, Pageable pageable);
    List<Project> findTop3ByOrderByDateDesc();
}