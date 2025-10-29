package ivan.zelezinski.lab_supplyer.controller;

import ivan.zelezinski.lab_supplyer.dto.AmountDto;
import ivan.zelezinski.lab_supplyer.service.BookClientService;
import ivan.zelezinski.lab_supplyer.service.MessageSender;
import ivan.zelezinski.lab_supplyer.utils.Urls;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Urls.SUPPLY.SUPPLY)
public class SupplyController {

    private final MessageSender messageSender;
    private final BookClientService bookClientService;

    @PostMapping(Urls.SUPPLY.QUEUE)
    public void sendBooksQueue(@RequestBody AmountDto dto) {
        messageSender.sendBooks(dto);
    }

    @PostMapping(Urls.SUPPLY.CLIENT)
    public void sendBooksClient(@RequestBody AmountDto dto) {
        bookClientService.sendBooks(dto);
    }

}
