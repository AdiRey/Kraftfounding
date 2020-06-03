package pl.kraft.project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
    @Query(value = "SELECT DISTINCT p.* FROM project p " +
            "LEFT JOIN project_abilities pa ON pa.project_id_project = p.id_project " +
            "INNER JOIN ability a ON pa.abilities_id_ability = a.id_ability " +
            "WHERE " +
            "(LOWER(p.title) LIKE LOWER('%:title%') " +
            "OR a.ability = ':ability') " +
            "AND p.completed = 'false'",
            countQuery = "SELECT COUNT(*) FROM project p  " +
                    "LEFT JOIN project_abilities pa ON pa.project_id_project = p.id_project " +
                    "INNER JOIN ability a ON pa.abilities_id_ability = a.id_ability " +
                    "WHERE " +
                    "(LOWER(p.title) LIKE LOWER('%:title%') " +
                    "OR a.ability = ':ability') " +
                    "AND p.completed = 'false'", nativeQuery = true)
    Page<Project> findAllPagination(@Param("title") String title, @Param("ability") String ability, Pageable pageable);
    List<Project> findTop3ByOrderByDateDesc();
}