package ivan.zelezinski.lab_supplyer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@ToString
@AllArgsConstructor
public class ExceptionMessageDto {

    private String message;

    private Integer code;

    private HttpStatus status;
}