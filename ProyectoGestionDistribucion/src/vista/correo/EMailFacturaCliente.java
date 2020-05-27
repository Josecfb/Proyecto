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
import entidades.FacturasCliente;

public class EMailFacturaCliente implements Runnable{
	private Properties props;
	private Session session;
	private String archivo;
	private FacturasCliente fac;
	
	public EMailFacturaCliente(FacturasCliente fac,String archivo) {
		this.archivo=archivo;
		this.fac=fac;
		props=new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.clave", decodif("%pssfQ3tjN$"));
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.port", "587");
	    System.out.println("enviando correo");
	    session = Session.getDefaultInstance(props);
	    Thread hilo=new Thread(this);
		hilo.start();
       
	    
	}

	@Override
	public void run() {
		BodyPart texto = new MimeBodyPart();
		 try {
				texto.setText("Estimado "+fac.getCliente().getNombre()+" le adjunto la factura correspondiente a su �ltimo pedido");
				BodyPart adjunto = new MimeBodyPart();
	            adjunto.setDataHandler(new DataHandler(new FileDataSource(archivo)));
	            adjunto.setFileName(archivo);
	            MimeMultipart multiParte = new MimeMultipart();
	            multiParte.addBodyPart(texto);
	            multiParte.addBodyPart(adjunto);
	            MimeMessage mensaje = new MimeMessage(session);
	            mensaje.setFrom(new InternetAddress("cursillos2002@gmail.com"));
	            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(fac.getCliente().getEmail()));
	            mensaje.setSubject("Pedido");
	            mensaje.setContent(multiParte);
	            Transport t = session.getTransport("smtp");
	            t.connect("cursillos2002@gmail.com", decodif("%pssfQ3tjN$"));
	            t.sendMessage(mensaje, mensaje.getAllRecipients());
	            t.close();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public static String decodif(String cadena) {
		String deco="";
		for (int i=cadena.length()-1;i>=0;i--)
			deco=deco.concat(Character.toString((char)(int)(cadena.charAt(i)-1)));
		return deco;
	}
}
