package pl.kraft.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.kraft.ability.AbilityDto;
import pl.kraft.ability.service.AbilityService;
import pl.kraft.exception.NotExistingIdException;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/abilities")
public class AbilityController {

    private AbilityService abilityService;

    public AbilityController(AbilityService abilityService) {
        this.abilityService = abilityService;
    }

    @GetMapping("/{id}")
    public AbilityDto getAbilityById(@PathVariable(name = "id") final Long id) {
        try {
            return abilityService.getAbilityById(id);
        } catch (NotExistingIdException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no ability with that id.");
        }
    }

    @GetMapping("")
    public List<AbilityDto> getAllAbilities() {
        return abilityService.getAllAbilities();
    }


    @PostMapping("")
    public ResponseEntity<AbilityDto> save(@RequestBody @Valid final AbilityDto dto) {
        AbilityDto savedAbility = abilityService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(savedAbility.getId()).toUri();
        return ResponseEntity.created(location).body(savedAbility);
    }

    @PostMapping("/{id}")
    public void savingUnderSpecifiedId(@PathVariable(name = "id") final Long id) {
        throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "You cannot save ability under any id.");
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

    @ExceptionHandler(NotExistingIdException.class)
    public String handleNotExistingIdException(NotExistingIdException ex) {
        return "There is no subject under this id.";
    }
}
