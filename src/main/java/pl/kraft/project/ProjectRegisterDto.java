package pl.kraft.project;

import pl.kraft.ability.AbilityDto;
import pl.kraft.student.StudentProjectDto;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

public class ProjectRegisterDto {
    @Null(message = "Id has to be null.")
    private Long id;
    @NotBlank(message = "Title may not be blank")
    private String title;
    @NotBlank(message = "Description may not be blank")
    private String description;
    @Valid
    private StudentProjectDto student;
    @Min(value = 1, message = "Minimum value is 1.")
    @Max(value = 100, message = "Maximum value is 100.")
    private Integer limit;
    private Boolean completed = false;
    private List<@Valid AbilityDto> abilities = new ArrayList<>();

    public ProjectRegisterDto() {
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

    public StudentProjectDto getStudent() {
        return student;
    }

    public void setStudent(StudentProjectDto student) {
        this.student = student;
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

    public List<AbilityDto> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilityDto> abilities) {
        this.abilities = abilities;
    }
}
