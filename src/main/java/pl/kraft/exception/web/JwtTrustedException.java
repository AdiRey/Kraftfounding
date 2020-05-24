package pl.kraft.exception.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Token cannot be trusted.")
public class JwtTrustedException extends RuntimeException {
}
