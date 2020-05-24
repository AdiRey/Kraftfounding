package pl.kraft.subject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "There is a subject with that name and term.")
public class DuplicateSubjectException extends RuntimeException{
}
