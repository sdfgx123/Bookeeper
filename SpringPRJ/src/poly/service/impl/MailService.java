package poly.service.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.MailDTO;
import poly.service.IMailService;
import poly.util.CmmUtil;

@Service("MailService")
public class MailService implements IMailService {

	private Logger log = Logger.getLogger(this.getClass());
	
	final String host = "smtp.google.com";
	final String user = "sdfgx123@gmail.com";
	final String password = "";
	
	@Override
	public int doSendMail(MailDTO pDTO) {
		
		log.info(this.getClass().getName() + " .doSendMail start");
		
		int res = 1;
		
		if (pDTO == null) {
			pDTO = new MailDTO();
		}
		
		String toMail = CmmUtil.nvl(pDTO.getToMail());
		
		/*Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");*/
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.port", "25"); 
        props.put("mail.debug", "true"); 
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.smtp.starttls.enable","true"); 
        props.put("mail.smtp.EnableSSL.enable","true");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
        props.setProperty("mail.smtp.socketFactory.fallback", "false");   
        props.setProperty("mail.smtp.port", "465");   
        props.setProperty("mail.smtp.socketFactory.port", "465"); 
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));

			message.setSubject(CmmUtil.nvl(pDTO.getTitle()));

			message.setText(CmmUtil.nvl(pDTO.getContents()));

			Transport.send(message);
		} catch (MessagingException e) {
			log.info("[ERROR]" + this.getClass().getName() + ".doSendMail :" + e);
		} catch (Exception e) {
			log.info("[ERROR]" + this.getClass().getName() + ".doSendMail :" + e);
		}
		
		log.info(this.getClass().getName() + " .doSendMail end");
		
		return res;
		
	}
	
}
