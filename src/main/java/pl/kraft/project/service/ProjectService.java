package pl.kraft.project.service;

import org.springframework.data.domain.Page;
import pl.kraft.ability.AbilityDto;
import pl.kraft.project.ProjectDto;
import pl.kraft.project.ProjectRegisterDto;
import pl.kraft.student.StudentDto;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> getThreeLastAddedProjects();
    Page<ProjectDto> getAllNotCompletedProjects(int numberOfPage, int size, String sortText, String text, String abilityName);
    ProjectDto getProjectById(Long id);
    ProjectDto saveProject(ProjectRegisterDto dto);
    List<AbilityDto> getProjectAbilities(Long id);
    List<StudentDto> getProjectStudents(Long id);
}
