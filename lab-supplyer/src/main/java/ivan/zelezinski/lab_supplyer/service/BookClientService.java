package ivan.zelezinski.lab_supplyer.service;

import ivan.zelezinski.lab_supplyer.dto.AmountDto;
import ivan.zelezinski.lab_supplyer.dto.BookDto;
import ivan.zelezinski.lab_supplyer.reader.BookReader;
import ivan.zelezinski.lab_supplyer.utils.Urls;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookClientService {

    private final BookReader bookReader;
    private final ForkJoinPool forkJoinPool =new ForkJoinPool(8);

    @Value("${lab-supplier.url}")
    private String serviceUrl;
    @Value("${lab-supplier.port}")
    private String servicePort;
    @Value("${lab-supplier.scheme}")
    private String serviceScheme;

    public Long getBookAmount() {
        RestClient restClient = RestClient.create();
        return restClient.get()
                .uri(uri -> uri.scheme("http")
                        .host(serviceUrl)
                        .port(servicePort)
                        .path(Urls.BOOK.AMOUNT)
                        .build())
                .retrieve()
                .body(Long.class);
    }

    public void sendBooks(AmountDto params) {
        List<BookDto> dtos = bookReader.readFile();
        RestClient restClient = RestClient.create();
        Function<UriBuilder, URI> url = uriBuilder -> uriBuilder
                .scheme(serviceScheme)
                .host(serviceUrl)
                .port(servicePort)
                .path(Urls.BOOK.BOOK)
                .build();

        forkJoinPool.submit(() -> dtos.parallelStream().forEach(dto -> {
            try {
                restClient.post()
                        .uri(url)
                        .body(dto)
                        .retrieve()
                        .body(BookDto.class);
            } catch (Exception e) {
                log.error("Error in books by client sending {}", e.getMessage());
            }
        }));
    }
}
