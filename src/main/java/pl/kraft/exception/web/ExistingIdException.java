package pl.kraft.exception.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "You cannot create object with id.")
public class ExistingIdException extends RuntimeException {
}
