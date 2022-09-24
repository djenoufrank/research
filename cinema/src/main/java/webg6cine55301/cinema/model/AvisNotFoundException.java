package webg6cine55301.cinema.model;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//je ne l'ai mm plus finalement utilisé :D
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "average movie not found")
public class AvisNotFoundException extends RuntimeException {//404 error
}
