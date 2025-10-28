package ivan.zelezinski.lab_supplyer.exceptions;

import ivan.zelezinski.lab_supplyer.dto.ExceptionMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReadFileException.class)
    public ResponseEntity<ExceptionMessageDto> badRequestException(ReadFileException exception) {
        return new ResponseEntity<>(new ExceptionMessageDto(
                exception.getMessage(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST
        );
    }
}
