package pl.kraft.project;

import pl.kraft.student.StudentProjectMapper;

public class ProjectMapper {
    public static Project map(ProjectDto dto) {
        Project project = new Project();
        project.setId(dto.getId());
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setLimit(dto.getLimit());
        project.setCompleted(dto.getCompleted());
        project.setStudent(StudentProjectMapper.map(dto.getStudentDto()));
        project.setDate(dto.getDate());
        return project;
    }

    public static ProjectDto map(Project project) {
        ProjectDto dto = new ProjectDto();
        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setDescription(project.getDescription());
        dto.setLimit(project.getLimit());
        dto.setCompleted(project.getCompleted());
        dto.setStudentDto(StudentProjectMapper.map(project.getStudent()));
        dto.setDate(project.getDate());
        return dto;
    }
}
