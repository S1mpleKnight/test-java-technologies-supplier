package ivan.zelezinski.lab_supplyer;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class LabSupplyerApplication {

    private final RabbitAdmin rabbitAdmin;
    private final List<Binding> bindings;

    @PostConstruct
    public void declareQueue() {
        bindings.forEach(rabbitAdmin::declareBinding);
    }


	public static void main(String[] args) {
		SpringApplication.run(LabSupplyerApplication.class, args);
	}

}
