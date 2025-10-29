package ivan.zelezinski.lab_supplyer.controller;

import ivan.zelezinski.lab_supplyer.service.BookClientService;
import ivan.zelezinski.lab_supplyer.utils.Urls;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Urls.STATISTICS.STATISTICS)
public class StatisticsController {

    private final BookClientService bookClientService;

    @GetMapping(Urls.BOOK.BOOK)
    public Long getBooksAmount() {
        return bookClientService.getBookAmount();
    }
}
