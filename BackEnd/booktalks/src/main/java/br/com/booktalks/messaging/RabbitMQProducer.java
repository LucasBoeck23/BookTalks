package br.com.booktalks.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendEmailMessage(String email) {
    	rabbitTemplate.convertAndSend("direct-exchange", "email", email);
    }
    public void sendEmailBoasVindas(String email, String subject, String name) {
        String message[] = {email,subject,name}; 
        rabbitTemplate.convertAndSend("direct-exchange", "email-BemVindo-queue", message);
    }
    public void sendEmailLivroPublicado(String email, String subject, String name) {
    	String message[] = {email,subject,name}; 
    	rabbitTemplate.convertAndSend("direct-exchange", "email-LivroPublicado-queue", message);
    }

}
