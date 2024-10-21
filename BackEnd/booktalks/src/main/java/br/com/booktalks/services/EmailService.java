package br.com.booktalks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
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
    public String UrlBotao;
    public String HTMLconteudo = "<html lang=\"pt-br\">\r\n"
    		+ "  <head>\r\n"
    		+ "    <meta charset=\"UTF-8\" />\r\n"
    		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n"
    		+ "    <title>Email</title>\r\n"
    		+ "  </head>\r\n"
    		+ "  <body style=\"background-color: rgba(232, 232, 232, 0.897);\">\r\n"
    		+ "    <div\r\n"
    		+ "      style=\"\r\n"
    		+ "        width: 600px;\r\n"
    		+ "        margin: 0 auto;\r\n"
    		+ "        border: 1px solid #dddddd;\r\n"
    		+ "        background-color: white;\r\n"
    		+ "      \"\r\n"
    		+ "    >\r\n"
    		+ "      <div style=\" font-family:sans-serif ; padding: 10px;\">\r\n"
    		+ "        \r\n"
    		+ "        <div style=\"height: 100px; background-color: black; color: orange; justify-content: flex-start;display: flex; align-items: center; padding-left: 20px; \">\r\n"
    		+ "          <h1 style=\"margin: 0;padding-top: 30px;padding-bottom: 30px;\">Booktalks</h1>\r\n"
    		+ "        </div>\r\n"
    		+ "        <div style=\"padding-left: 20px;\">\r\n"
    		+ "          <h2>%s</h2>\r\n"
    		+ "          <p>Olá <strong>%s</strong>,</p>\r\n"
    		+ "          <p>\r\n"
    		+ "            %s\r\n"
    		+ "          </p>\r\n"
    		+ "          <p>Atenciosamente, equipe Booktalks</p>\r\n"
    		+ "        </div>\r\n"
    		+ "        <div style=\"text-align: center; width: 100%%;\">\r\n"
    		+ "          <a\r\n"
    		+ "            href=\"%s\"\r\n"
    		+ "            class=\"cta\"\r\n"
    		+ "            style=\"\r\n"
    		+ "              display: inline-block;\r\n"
    		+ "              padding: 15px 20px;\r\n"
    		+ "              text-decoration: none;\r\n"
    		+ "              background-color: black;\r\n"
    		+ "              color: orange;\r\n"
    		+ "              border-radius: 8px;\r\n"
    		+ "              font-family: sans-serif;\r\n"
    		+ "              font-size: 16px;\r\n"
    		+ "              margin: 0 auto;\r\n"
    		+ "            \"\r\n"
    		+ "            >%s</a>\r\n"
    		+ "            \r\n"
    		+ "        </div>\r\n"
    		+ "      </div>\r\n"
    		+ "    </div>\r\n"
    		+ "  </body>\r\n"
    		+ "</html>";
    public void emailBoasVindas(String destinatario, String assunto, String pessoaNome) throws MessagingException{
    	MimeMessage mensagem = mailSender.createMimeMessage();
    	MimeMessageHelper email = new MimeMessageHelper(mensagem, true, "UTF-8");
    	
    	TituloEmail = "Boas Vindas!";
        NomeDestinatario = pessoaNome;
        TituloBotao="Abrir Booktalks";
        UrlBotao = "https://www.youtube.com/watch?v=_fr4SV4fGAw";
        Texto = """
        		Estamos muito felizes em tê-lo conosco! Esperamos que você se sinta em
        		casa por aqui. Nossa comunidade está cheia de pessoas incríveis e dispostas a conversar com você!

        		Qualquer coisa que precisar, estamos à disposição para ajudar você a tirar o melhor proveito da sua experiência. Aproveite tudo o que temos
        		a oferecer e explore as diversas funcionalidades disponíveis.
        		""";
        
        String htmlFormatado = String.format(HTMLconteudo, TituloEmail, pessoaNome, Texto,UrlBotao,TituloBotao);
       
        try {
        	email.setTo(destinatario);
        	email.setSubject(assunto);
        	email.setText(htmlFormatado, true);
        	mailSender.send(mensagem);
		} catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao tentar enviar o email: " + e.getLocalizedMessage());
		}
    	
    }
    public void emailPublicaLivro(String destinatario, String assunto, String pessoaNome) throws MessagingException{
    	MimeMessage mensagem = mailSender.createMimeMessage();
    	MimeMessageHelper email = new MimeMessageHelper(mensagem, true, "UTF-8");
    	
    	TituloEmail = "Publicação Realizada";
    	NomeDestinatario = pessoaNome;
    	TituloBotao="Conferir Livro";
    	UrlBotao = "https://a.co/d/3VQRKTq";
    	
    	Texto = """
        		Sua publicação foi concluida! Gostariamos de agradeçer por sempre escolher a Booktalks
        		
        		Sem pessoas iguais a você isso não seria possivel, continue ativo por aqui, porque sempre havera uma novidade
        		""";
    	
    	String htmlFormatado = String.format(HTMLconteudo, TituloEmail, pessoaNome, Texto, UrlBotao,TituloBotao);
    	
    	try {
    		email.setTo(destinatario);
    		email.setSubject(assunto);
    		email.setText(htmlFormatado, true);
    		
    		mailSender.send(mensagem);
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("Erro ao tentar enviar o email: " + e.getLocalizedMessage());
    	}
    	
    }
    
    //FAZER NOVO ENVIO AQUI
//    public void email(String destinatario, String assunto, String pessoaNome) throws MessagingException{
//    	MimeMessage mensagem = mailSender.createMimeMessage();
//    	MimeMessageHelper email = new MimeMessageHelper(mensagem, true, "UTF-8");
//    	
//    	TituloEmail = "Publicação Realizada";
//    	NomeDestinatario = pessoaNome;
//    	TituloBotao="Conferir Livro";
//    	UrlBotao = "https://a.co/d/3VQRKTq";
//    	
//    	Texto = """
//        		Sua publicação foi concluida! Gostariamos de agradeçer por sempre escolher a Booktalks
//        		
//        		Sem pessoas iguais a você isso não seria possivel, continue ativo por aqui, porque sempre havera uma novidade
//        		""";
//    	
//    	String htmlFormatado = String.format(HTMLconteudo, TituloEmail, pessoaNome, Texto, UrlBotao,TituloBotao);
//    	
//    	try {
//    		email.setTo(destinatario);
//    		email.setSubject(assunto);
//    		email.setText(htmlFormatado, true);
//    		
//    		mailSender.send(mensagem);
//    	} catch (Exception e) {
//    		e.printStackTrace();
//    		System.out.println("Erro ao tentar enviar o email: " + e.getLocalizedMessage());
//    	}
//    	
//    }
}
