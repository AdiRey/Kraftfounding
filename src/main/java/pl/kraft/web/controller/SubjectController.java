package pl.kraft.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kraft.exception.NotExistingIdException;
import pl.kraft.subject.SubjectDto;
import pl.kraft.subject.service.SubjectService;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @ApiOperation(value = "Return list of subjects",
            notes = "Pattern: /api/subjects?type={typeName} \n Available types (typeName): \n - all " +
                    "(all subjects and their terms) \n - unique (distinct subjects' name)")
    @GetMapping("")
    public List<SubjectDto> getAllSubjects(@RequestParam(required = false, defaultValue = "all") String type) {
        List<SubjectDto> subjectDtoList;
        if (type.equals("all")) {
            subjectDtoList = subjectService.getAllSubject();
        } else if(type.equals("unique")) {
            subjectDtoList = subjectService.getAllSubjectNames();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Bad type.");
        }
        return subjectDtoList;
    }

    @ApiOperation(value = "Return single subject",
            notes = "Pattern: /api/subjects/{id} \n Id is a numeric value and it's greater than or equals to 1")
    @GetMapping("/{id}")
    public SubjectDto getSubjectById(@PathVariable(name = "id") final Long id) {
        return subjectService.findById(id);
    }

    @ApiOperation(value= "Register a new subject in the service.")
    @PostMapping("/")
    public ResponseEntity<SubjectDto> saveSubject(@RequestBody @Valid final SubjectDto dto) {
        SubjectDto savedDto = subjectService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(savedDto.getId()).toUri();
        return ResponseEntity.created(location).body(savedDto);
    }

    @ApiOperation("Blocked api endpoint, don't use it.")
    @PostMapping("/{id}")
    public void savingUnderSpecifiedId(@PathVariable(name = "id") final Long id) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "You cannot save subject under any id.");
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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotExistingIdException.class)
    public String handleNotIdException(NotExistingIdException ex) {
        return "There is no subject under this id.";
    }
}
