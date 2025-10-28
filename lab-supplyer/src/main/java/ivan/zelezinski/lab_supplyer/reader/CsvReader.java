package ivan.zelezinski.lab_supplyer.reader;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Getter
public abstract class CsvReader<T> {

    @Value("${lab-supplyer.csv.separator}")
    protected String textSeparator;

    public abstract List<T> readFile();

}
