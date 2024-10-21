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
	     Queue emailBemVindoQueue() {
	        return new Queue("email-BemVindo-queue", true);
	    }
	    @Bean
	    Queue emailLivroPublicadoQueue() {
	    	return new Queue("email-LivroPublicado-queue", true);
	    }

	    @Bean
	     DirectExchange directExchange() {
	        return new DirectExchange("direct-exchange");
	    }

	    @Bean
	     Binding emailBemVindoBinding() {
	        return BindingBuilder.bind(emailBemVindoQueue()).to(directExchange()).with("email-BemVindo-queue");
	    }
	    @Bean
	    Binding emailLivroPublicadoBinding() {
	    	return BindingBuilder.bind(emailLivroPublicadoQueue()).to(directExchange()).with("email-LivroPublicado-queue");
	    }

}
