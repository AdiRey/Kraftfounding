package pl.kraft.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kraft.academicWorker.AcademicWorkerDto;
import pl.kraft.academicWorker.AcademicWorkerRegisterDto;
import pl.kraft.academicWorker.service.AcademicWorkerService;
import pl.kraft.exception.InvalidIdException;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/workers")
public class AcademicWorkerController {
    private AcademicWorkerService academicWorkerService;

    public AcademicWorkerController(AcademicWorkerService academicWorkerService) {
        this.academicWorkerService = academicWorkerService;
    }

    @PostMapping("")
    public ResponseEntity<AcademicWorkerDto> register(@RequestBody @Valid final AcademicWorkerRegisterDto dto) {
        AcademicWorkerDto savedAcademicWorker = academicWorkerService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(savedAcademicWorker.getId()).toUri();
        return ResponseEntity.created(location).body(savedAcademicWorker);
    }

    @PostMapping("/{id}")
    public void registerUnderSpecifyingId(@PathVariable(name = "id")final Long id) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot do this.");
    }

    @GetMapping("")
    public Page<AcademicWorkerDto> getAllStudents(@RequestParam(required = false, defaultValue = "0") final Integer page,
                                               @RequestParam(required = false, defaultValue = "surname") final String column,
                                               @RequestParam(required = false, defaultValue = "ASC") final String sort,
                                               @RequestParam(required = false, defaultValue = "") final String filter) {
        return academicWorkerService.findAllAcademicWorkersUsingPaging(page, column, sort, filter);
    }

    @GetMapping("/{id}")
    public AcademicWorkerDto getStudentById(@PathVariable(name = "id") final Long id) {
        try {
            return academicWorkerService.findAcademicWorkerById(id);
        } catch (InvalidIdException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with that id doesn't exist.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcademicWorkerDto> editStudent(@PathVariable(name = "id") final Long id,
                                                  @Valid @RequestBody final AcademicWorkerDto dto) {
        return ResponseEntity.ok(academicWorkerService.editAcademicWorker(id, dto));
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

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(NumberFormatException.class)
    public String handleNumberFormatException(NumberFormatException ex) {
        return "You must use a numeric value.";
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(PropertyReferenceException.class)
    public String handlePropertyReferenceException(PropertyReferenceException ex){
        return "Wrong atribute.";
    }
}
