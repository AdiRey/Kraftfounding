package pl.kraft.project;

import org.springframework.format.annotation.DateTimeFormat;
import pl.kraft.student.StudentDto;
import pl.kraft.student.StudentProjectDto;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class ProjectDto {
    @NotNull(message = "Id has to be not null.")
    private Long id;
    @NotBlank(message = "Title may not be blank")
    private String title;
    @NotBlank(message = "Description may not be blank")
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime date;
    @Min(value = 1, message = "Minimum value is 1.")
    @Max(value = 100, message = "Maximum value is 100.")
    private Integer limit;
    private Boolean completed;
    private StudentProjectDto studentDto;

    public ProjectDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public StudentProjectDto getStudentDto() {
        return studentDto;
    }

    public void setStudentDto(StudentProjectDto studentDto) {
        this.studentDto = studentDto;
    }
}
