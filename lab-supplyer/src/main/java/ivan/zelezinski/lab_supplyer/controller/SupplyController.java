package ivan.zelezinski.lab_supplyer.controller;

import ivan.zelezinski.lab_supplyer.dto.SendParamsDto;
import ivan.zelezinski.lab_supplyer.service.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("supply/")
public class SupplyController {

    private final MessageSender messageSender;

    @PostMapping("books/")
    public void sendBooks(@RequestBody SendParamsDto dto) {
        messageSender.sendBook(dto);
    }

}
