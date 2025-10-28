package ivan.zelezinski.lab_supplyer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BookDto {

    private String name;
    private LocalDate releaseDate;
}
