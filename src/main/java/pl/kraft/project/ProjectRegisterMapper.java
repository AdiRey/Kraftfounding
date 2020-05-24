package pl.kraft.project;

import pl.kraft.ability.AbilityMapper;
import pl.kraft.student.StudentProjectMapper;

import java.util.stream.Collectors;

public class ProjectRegisterMapper {
    public static Project map(ProjectRegisterDto dto) {
        Project project = new Project();
        project.setId(dto.getId());
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setLimit(dto.getLimit());
        project.setStudent(StudentProjectMapper.map(dto.getStudent()));
        project.setCompleted(dto.getCompleted());
        project.setAbilities(dto.getAbilities().stream()
                .map(AbilityMapper::map).collect(Collectors.toList()));
        return project;
    }

    public static ProjectRegisterDto map(Project project) {
        ProjectRegisterDto dto = new ProjectRegisterDto();
        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setDescription(project.getDescription());
        dto.setLimit(project.getLimit());
        dto.setStudent(StudentProjectMapper.map(project.getStudent()));
        dto.setCompleted(project.getCompleted());
        dto.setAbilities(project.getAbilities().stream()
                .map(AbilityMapper::map).collect(Collectors.toList()));
        return dto;
    }
}
