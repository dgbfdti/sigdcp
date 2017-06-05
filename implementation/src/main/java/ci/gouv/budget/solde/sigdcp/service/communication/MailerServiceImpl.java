package ci.gouv.budget.solde.sigdcp.service.communication;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ci.gouv.budget.solde.sigdcp.model.communication.NotificationMessage;
import ci.gouv.budget.solde.sigdcp.model.identification.Party;
import ci.gouv.budget.solde.sigdcp.service.utils.communication.MailService;

public class MailerServiceImpl implements MailService, Serializable {

	private static final long serialVersionUID = -8680313005464068114L;
	
	public static String MAIL_USERNAME = "soldesigdcp@gmail.com";
	public static String MAIL_PASSWORD = "sigdcp1234";
	
	public static String MAIL_FROM = MAIL_USERNAME;
	public static String MAIL_SMTP_AUTH = "true";
	public static String MAIL_SMTP_STARTTLS_ENABLE = "true";
	public static String MAIL_SMTP_SSL_ENABLE = "true";
	public static String MAIL_SMTP_HOST = "74.125.202.108";
	//public static String MAIL_SMTP_HOST = "smtp.gmail.com";
	public static String MAIL_SMTP_PORT = "465";
	
	//@Resource(lookup = "java:/sigdcpMail")
	//@Resource(lookup = "mail/sigdcp")
    //private Session session;
	
	private Session getSession(Boolean debug) {
    	Session session = null;
    	Properties properties = new Properties();
    	properties.put("mail.from", MAIL_FROM);
    	properties.put("mail.smtp.auth", MAIL_SMTP_AUTH);
    	properties.put("mail.smtp.starttls.enable", MAIL_SMTP_STARTTLS_ENABLE);
    	properties.put("mail.smtp.ssl.enable", MAIL_SMTP_SSL_ENABLE);
    	properties.put("mail.smtp.host", MAIL_SMTP_HOST);
    	properties.put("mail.smtp.port", MAIL_SMTP_PORT);
    	//properties.put("mail.smtp.socks.host", "10.3.4.5");
    	//properties.put("mail.smtp.socks.port", "3128");
    	
    	//System.out.println("Mail session created : "+properties);
    	session = Session.getInstance(properties,new Authenticator() {
    		@Override
    		protected PasswordAuthentication getPasswordAuthentication() {
    			return new PasswordAuthentication(MAIL_USERNAME, MAIL_PASSWORD);
    		}
		});
    	session.setDebug(debug);
    	return session;
		
    }

	@Override
	public void send(NotificationMessage message, String receiver) {
		send(message, new String[]{receiver});
	}
		
	public void send(NotificationMessage mailMessage,String[] receivers)  { 
		send(mailMessage, addresses(receivers));
	}
	
	public void send(NotificationMessage mailMessage,Collection<String> receivers) {
		send(mailMessage, addresses(receivers));
	}
	
	private InternetAddress[] addresses(String...theAddresses){
		Collection<InternetAddress> receiverAddresses = new LinkedList<>();
		for(String adrress : theAddresses)
			try {
				receiverAddresses.add(new InternetAddress(adrress));
			} catch (AddressException e) {
				e.printStackTrace();
			}
		return receiverAddresses.toArray(new InternetAddress[]{});
	}
	
	private InternetAddress[] addressesParty(Party...parties){
		Collection<String> receiverAddresses = new LinkedList<>();
		for(Party party : parties)
			receiverAddresses.add(party.getContact().getEmail());
		return addresses(receiverAddresses);
	}
	
	private InternetAddress[] addresses(Collection<String> receivers){
		return addresses(receivers.toArray(new String[]{}));
	}
	
	private InternetAddress[] addressesParty(Collection<Party> receivers){
		return addressesParty(receivers.toArray(new Party[]{}));
	}
	
	private void send(final NotificationMessage mailMessage,final InternetAddress[] receivers) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Session session = getSession(Boolean.FALSE);
				MimeMessage message = new MimeMessage(session);
				try {
					message.setFrom(new InternetAddress(session.getProperty("mail.from")));
					message.setRecipients(Message.RecipientType.TO, receivers);
					message.setSubject(mailMessage.getSubject());
					message.setSentDate(new Date());
					message.setContent(mailMessage.getBody(), "text/html; charset=utf-8");
					//message.setText(mailMessage.getBody());
					Transport.send(message);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}).start();
		
	}
	
	@Override
	public void send(NotificationMessage message, Party[] receivers) {
		send(message, addressesParty(receivers));
	}

	@Override
	public void send(NotificationMessage message, Party receiver) {
		send(message, new Party[]{receiver});
	}

	@Override
	public void sendParty(NotificationMessage message, Collection<Party> receivers) {
		send(message, addressesParty(receivers));
	}
    
    public void test(){
    	try {
			send(new NotificationMessage("Configuration Exchange 2010 SMTP Relay","http://www.youtube.com/watch?v=gj61RzL_WFc"),  new String[]{"komenanyc@yahoo.com"});
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	try {
			send(new NotificationMessage("Configuration Exchange 2010 SMTP Relay","http://www.youtube.com/watch?v=gj61RzL_WFc"),  new String[]{"christian.komenan@budget.gouv.ci"});
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }


    
}