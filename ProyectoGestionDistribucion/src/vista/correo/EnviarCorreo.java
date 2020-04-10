package vista.correo;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnviarCorreo implements Runnable{
	private Properties props;
	private Session session;
	private String para,archivo;
	
	public EnviarCorreo(String para,String archivo) {
		this.archivo=archivo;
		this.para=para;
		props=new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.user", "cursillos2002@gmail.com");
	    props.put("mail.smtp.clave", "#Mis2Perro$");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.port", "587");
	    
	    session = Session.getDefaultInstance(props);
	    Thread hilo=new Thread(this);
		hilo.start();
       
	    
	}

	@Override
	public void run() {
		BodyPart texto = new MimeBodyPart();
		 try {
				texto.setText("Le adjunto pedido");
				BodyPart adjunto = new MimeBodyPart();
	            adjunto.setDataHandler(new DataHandler(new FileDataSource(archivo)));
	            adjunto.setFileName(archivo);
	            MimeMultipart multiParte = new MimeMultipart();
	            multiParte.addBodyPart(texto);
	            multiParte.addBodyPart(adjunto);
	            MimeMessage mensaje = new MimeMessage(session);
	            mensaje.setFrom(new InternetAddress("cursillos2002@gmail.com"));
	            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
	            mensaje.setSubject("Pedido");
	            mensaje.setContent(multiParte);
	            Transport t = session.getTransport("smtp");
	            t.connect("cursillos2002@gmail.com", "#Mis2Perro$");
	            t.sendMessage(mensaje, mensaje.getAllRecipients());
	            t.close();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
