package ivan.zelezinski.lab_supplyer.service;

import ivan.zelezinski.lab_supplyer.dto.BookDto;
import ivan.zelezinski.lab_supplyer.dto.SendParamsDto;
import ivan.zelezinski.lab_supplyer.reader.BookReader;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

@Service
@Setter
@RequiredArgsConstructor
public class MessageSender {

    private final AmqpTemplate amqpTemplate;
    private final BookReader bookReader;

    @Value("${lab-supplyer.books.routing.key.first}")
    private String firstRoutingKey;
    @Value("${lab-supplyer.books.routing.key.second}")
    private String secondRoutingKey;
    @Value("${lab-supplyer.books.exchange}")
    private String bookExchangeName;

    public void sendBook(SendParamsDto params) {
        List<BookDto> dtos = bookReader.readFile();

        try (ForkJoinPool forkJoinPool = new ForkJoinPool(8)) {
            for (int i = 0; i < params.getTimes(); i++) {
                dtos.forEach(dto -> forkJoinPool
                        .submit(() -> amqpTemplate.convertAndSend(bookExchangeName, firstRoutingKey, dto)));
                dtos.forEach(dto -> forkJoinPool
                        .submit(() -> amqpTemplate.convertAndSend(bookExchangeName, secondRoutingKey, dto)));
            }
            forkJoinPool.shutdown();
            forkJoinPool.awaitQuiescence(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }
    }
}
