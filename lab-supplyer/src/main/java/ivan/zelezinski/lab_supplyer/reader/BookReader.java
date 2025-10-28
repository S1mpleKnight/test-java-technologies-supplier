package ivan.zelezinski.lab_supplyer.reader;

import ivan.zelezinski.lab_supplyer.dto.BookDto;
import ivan.zelezinski.lab_supplyer.exceptions.ReadFileException;
import ivan.zelezinski.lab_supplyer.utils.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

@Service
public class BookReader extends CsvReader<BookDto> {

    @Value("${lab-supplyer.books.file}")
    private String booksFile;

    @Override
    public List<BookDto> readFile() {
        try {
            return Files.readAllLines(Path.of(booksFile))
                    .stream()
                    .map(line -> {
                        String[] split = line.split(textSeparator);
                        return new BookDto(split[0].trim(), LocalDate.parse(split[1].trim(),
                                DateTimeFormatter.ofPattern(Constants.DATE_REGEXP)));
                    })
                    .toList();
        } catch (IOException e) {
            throw new ReadFileException(e.getMessage());
        }
    }
}
