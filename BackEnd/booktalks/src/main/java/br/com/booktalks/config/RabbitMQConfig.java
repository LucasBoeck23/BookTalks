package br.com.booktalks.config;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {


	    @Bean
	     Queue emailQueue() {
	        return new Queue("email-queue", true);
	    }

	    @Bean
	     DirectExchange directExchange() {
	        return new DirectExchange("direct-exchange");
	    }

	    @Bean
	     Binding emailBinding() {
	        return BindingBuilder.bind(emailQueue()).to(directExchange()).with("email-queue");
	    }

}
