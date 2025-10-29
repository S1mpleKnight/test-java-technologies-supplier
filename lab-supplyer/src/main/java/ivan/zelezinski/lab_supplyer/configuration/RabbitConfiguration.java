package ivan.zelezinski.lab_supplyer.configuration;

import lombok.Setter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Setter
public class RabbitConfiguration {

    @Value("${lab-supplier.queue.book.name.first}")
    private String firstBookQueueName;
    @Value("${lab-supplier.queue.book.name.second}")
    private String secondBookQueueName;
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${lab-supplier.books.exchange}")
    private String bookExchangeName;
    @Value("${lab-supplier.books.routing.key.first}")
    private String firstRoutingKey;
    @Value("${lab-supplier.books.routing.key.second}")
    private String secondRoutingKey;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue firstBookQueue() {
        return new Queue(firstBookQueueName);
    }

    @Bean
    public Queue secondBookQueue() {
        return new Queue(secondBookQueueName);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(bookExchangeName);
    }

    @Bean
    public Binding firstBookDirect() {
        return BindingBuilder
                .bind(firstBookQueue())
                .to(directExchange())
                .with(firstRoutingKey);
    }

    @Bean
    public Binding secondBookDirect() {
        return BindingBuilder
                .bind(secondBookQueue())
                .to(directExchange())
                .with(secondRoutingKey);
    }
}

