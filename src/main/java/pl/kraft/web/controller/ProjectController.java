package pl.kraft.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kraft.ability.AbilityDto;
import pl.kraft.project.ProjectDto;
import pl.kraft.project.ProjectRegisterDto;
import pl.kraft.project.service.ProjectService;
import pl.kraft.student.StudentDto;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/threeAdded/projects")
    public List<ProjectDto> getThreeLastAddedProjects() {
        return projectService.getThreeLastAddedProjects();
    }

    @GetMapping("")
    @ApiOperation(value = "Get all projects paginated", notes = "Test 1 2 3")
    public Page<ProjectDto> getAllProjects(@RequestParam(required = false, defaultValue = "0") final Integer page,
                                       @RequestParam(required = false, defaultValue = "ASC") final String sort,
                                       @RequestParam(required = false, defaultValue = "") final String filter) {
        return projectService.getAllNotCompletedProjects(page, sort, filter);
    }

    @GetMapping("/{id}")
    public ProjectDto getProjectById(@PathVariable(name = "id") final Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/{id}/abilities")
    public List<AbilityDto> getProjectAbilitiesById(@PathVariable(name = "id") final Long id) {
        return projectService.getProjectAbilities(id);
    }

    @GetMapping("/{id}/students")
    public List<StudentDto> getProjectStudentsById(@PathVariable(name = "id") final Long id) {
        return projectService.getProjectStudents(id);
    }

    @PostMapping("")
    public ResponseEntity<ProjectDto> saveProject(@RequestBody @Valid final ProjectRegisterDto dto) {
        ProjectDto savedProject = projectService.saveProject(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(savedProject.getId()).toUri();
        return ResponseEntity.created(location).body(savedProject);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
