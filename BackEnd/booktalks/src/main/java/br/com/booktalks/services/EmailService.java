package br.com.booktalks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public String TituloEmail;
    public String NomeDestinatario;
    public String Texto;
    public String TituloBotao;
    public String HTMLconteudo = "<html lang=\"pt-br\">\r\n"
    		+ "  <head>\r\n"
    		+ "    <meta charset=\"UTF-8\" />\r\n"
    		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n"
    		+ "    <title>Email</title>\r\n"
    		+ "  </head>\r\n"
    		+ "  <body>\r\n"
    		+ "    <div\r\n"
    		+ "      style=\"\r\n"
    		+ "        max-width: 600px;\r\n"
    		+ "        margin: 0 auto;\r\n"
    		+ "        border: 1px solid #dddddd;\r\n"
    		+ "        background-color: #dcdcdc;\r\n"
    		+ "      \"\r\n"
    		+ "    >\r\n"
    		+ "      <div\r\n"
    		+ "        style=\"\r\n"
    		+ "          justify-content: center;\r\n"
    		+ "          width: 100%%;\r\n"
    		+ "          height: 20px;\r\n"
    		+ "          background-color: #2f4f4f;\r\n"
    		+ "        \"\r\n"
    		+ "      ></div>\r\n"
    		+ "      <div style=\"padding: 10px; font-family:sans-serif;\">\r\n"
    		+ "        <h1>%s</h1>\r\n"
    		+ "        <p>Olá <strong>%s</strong>,</p>\r\n"
    		+ "        <p>%s<p>"
    		+ "        <div style=\"justify-content: center; display: flex\">\r\n"
    		+ "                <a\r\n"
    		+ "                href=\"https://www.youtube.com/watch?v=e-ORhEE9VVg\"\r\n"
    		+ "                class=\"cta\"\r\n"
    		+ "                style=\"\r\n"
    		+ "              display: inline-block;\r\n"
    		+ "              align-items: center;\r\n"
    		+ "              justify-content: center;\r\n"
    		+ "              text-align: center;\r\n"
    		+ "              margin: 5px;\r\n"
    		+ "              margin-bottom: 5px;\r\n"
    		+ "              padding: 10px;\r\n"
    		+ "              width: 120px;\r\n"
    		+ "              text-decoration: none;\r\n"
    		+ "              background-color:#2f4f4f;\r\n"
    		+ "              color: #F8F8F8;\r\n"
    		+ "              border-radius: 8px;"
    		+ "				 "
    		+ "              \"\r\n"
    		+ "            >%s</a\r\n"
    		+ "            >\r\n"
    		+ "        </div>\r\n"
    		+ "      </div>\r\n"
    		+ "      <div\r\n"
    		+ "        style=\"\r\n"
    		+ "          justify-content: flex-end;\r\n"
    		+ "          width: 100%%;\r\n"
    		+ "          height: 20px;\r\n"
    		+ "          background-color: #2f4f4f;\r\n"
    		+ "        \"\r\n"
    		+ "      ></div>\r\n"
    		+ "    </div>\r\n"
    		+ "  </body>\r\n"
    		+ "</html>";
    
    public String enviarEmailTexto(String destinatario, String assunto, String mensagem) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(mensagem);
            System.out.println("Enviando email para: " + destinatario);
            mailSender.send(simpleMailMessage);
            System.out.println("Email enviado com sucesso para: " + destinatario);
            return "Email enviado";
        } catch (Exception e) {
            System.err.println("Erro ao tentar enviar o email para: " + destinatario);
            e.printStackTrace();
            return "Erro ao tentar enviar o email: " + e.getLocalizedMessage();
        }
    }
    
    public void emailBoasVindas(String destinatario, String assunto, String pessoaNome) throws MessagingException{
    	MimeMessage mensagem = mailSender.createMimeMessage();
    	MimeMessageHelper email = new MimeMessageHelper(mensagem, true, "UTF-8");
    	
    	TituloEmail = "Boas Vindas!";
        NomeDestinatario = pessoaNome;
        TituloBotao="Abrir Booktalks";
        Texto = """
        		Estamos muito felizes em tê-lo conosco! Esperamos que você se sinta em
        		casa por aqui. Nossa comunidade está cheia de pessoas incríveis e dispostas a conversar com você!

        		Qualquer coisa que precisar, estamos à disposição para ajudar você a tirar o melhor proveito da sua experiência. Aproveite tudo o que temos
        		a oferecer e explore as diversas funcionalidades disponíveis.
        		""";
        
        String htmlFormatado = String.format(HTMLconteudo, TituloEmail, pessoaNome, Texto, TituloBotao);
       
        try {
        	email.setTo(destinatario);
        	email.setSubject(assunto);
        	email.setText(htmlFormatado, true);
        	
        	mailSender.send(mensagem);
        	System.out.println("Email enviado com sucesso para: " + destinatario);
            System.out.println("Email enviado");
		} catch (Exception e) {
			System.err.println("Erro ao tentar enviar o email para: " + destinatario);
            e.printStackTrace();
            System.out.println("Erro ao tentar enviar o email: " + e.getLocalizedMessage());
		}
    	
    }
}
