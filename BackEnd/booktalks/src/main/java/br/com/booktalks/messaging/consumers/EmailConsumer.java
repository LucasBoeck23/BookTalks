package br.com.booktalks.messaging.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import br.com.booktalks.services.EmailService;
import jakarta.mail.MessagingException;

@Service
public class EmailConsumer {
	
	private final EmailService emailService;


	    public EmailConsumer(EmailService emailService) {
	        this.emailService = emailService;
	    }

    
	    @RabbitListener(queues = "email-BemVindo-queue")
	    public void consumeEmailBoasVindas(String message[]) throws MessagingException {
	        
	        String destinatario = message[0];
	        String assunto = message[1];
	        String pessoaNome = message[2];
	        emailService.emailBoasVindas(destinatario, assunto, pessoaNome);
	    }
	    @RabbitListener(queues = "email-LivroPublicado-queue")
	    public void consumeEmailLivroPublicado(String message[]) throws MessagingException {
	    	
	    	String destinatario = message[0];
	    	String assunto = message[1];
	    	String pessoaNome = message[2];
	    	emailService.emailPublicaLivro(destinatario, assunto, pessoaNome);
	    }

}
