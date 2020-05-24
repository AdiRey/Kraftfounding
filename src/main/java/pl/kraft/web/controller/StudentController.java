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
import pl.kraft.exception.InvalidIdException;
import pl.kraft.student.StudentDto;
import pl.kraft.student.StudentRegisterDto;
import pl.kraft.student.service.StudentService;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("")
    public ResponseEntity<StudentDto> register(@RequestBody @Valid final StudentRegisterDto dto) {
        StudentDto savedStudent = studentService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(savedStudent.getId()).toUri();
        return ResponseEntity.created(location).body(savedStudent);
    }

    @PostMapping("/{id}")
    public void registerUnderSpecifyingId(@PathVariable(name = "id")final Long id) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot do this.");
    }

    @GetMapping("")
    public Page<StudentDto> getAllStudents(@RequestParam(required = false, defaultValue = "0") final Integer page,
                                           @RequestParam(required = false, defaultValue = "surname") final String column,
                                           @RequestParam(required = false, defaultValue = "ASC") final String sort,
                                           @RequestParam(required = false, defaultValue = "") final String filter) {
        return studentService.findAllStudentsUsingPaging(page, column, sort, filter);
    }

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable(name = "id") final Long id) {
        try {
            return studentService.findStudentById(id);
        } catch (InvalidIdException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with that id doesn't exist.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> editStudent(@PathVariable(name = "id") final Long id,
                                                  @Valid @RequestBody final StudentDto dto) {
        return ResponseEntity.ok(studentService.editStudent(id, dto));
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
