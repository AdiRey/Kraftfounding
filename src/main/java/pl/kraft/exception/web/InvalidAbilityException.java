package pl.kraft.exception.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Nie istnieje umiejetnosc.")
public class InvalidAbilityException extends RuntimeException {

}
