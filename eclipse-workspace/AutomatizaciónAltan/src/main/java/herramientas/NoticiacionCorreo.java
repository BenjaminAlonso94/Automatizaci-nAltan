package herramientas;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NoticiacionCorreo {
	String to = "balonsmo@everis.com";
	String from = "balonsmo@everis.com";

	public static void main(String args[]) {
		NoticiacionCorreo ml = new NoticiacionCorreo();
		ml.emailEnvio("AL intentar consumir la API de Activate nos responde 500, el script de detuvo");
	}

	public void emailEnvio(String mensajeError) {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp-mail.outlook.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("balonsmo@everis.com", "Benja132**");
				}
			});
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from, "NoReply"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("it.operaciones@altanredes.com", "it.operaciones@altanredes.com"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("soporteapigw@altanredes.com", "soporteapigw@altanredes.com"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("alessandra.bomura@altanredes.com", "alessandra.bomura@altanredes.com"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("jose.carrillo@altanredes.com", "jose.carrillo@altanredes.com"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("ricardo.dominguez@altanredes.com", "ricardo.dominguez@altanredes.com"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("rosa.marquez@altanredes.com", "rosa.marquez@altanredes.com"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to, "balonsmo@everis.com"));
			msg.setSubject("ERROR en Automatización");
			msg.setText(mensajeError);
			Transport.send(msg);
			System.out.println("Email enviado satisfactoriamente...");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
