package ci.gouv.budget.solde.sigdcp.service.communication;

import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import ci.gouv.budget.solde.sigdcp.model.communication.NotificationMessage;
import ci.gouv.budget.solde.sigdcp.model.communication.NotificationMessageType;
import ci.gouv.budget.solde.sigdcp.model.identification.Party;
import ci.gouv.budget.solde.sigdcp.service.utils.TemplateEngineService;
import ci.gouv.budget.solde.sigdcp.service.utils.communication.MailService;
import ci.gouv.budget.solde.sigdcp.service.utils.communication.NotificationService;
import lombok.extern.java.Log;

@Log
public class NotificationServiceImpl implements NotificationService,Serializable {

	private static final long serialVersionUID = -4376077455219565698L;
	
	@Inject private TemplateEngineService templateEngineService;
	@Inject private Event<MailNotificationEvent> mailEventService;
	
	@Inject private MailService mailService;

	@Override
	public NotificationMessage build(NotificationMessageType messageType,Map<String, Object> parameters) {
		NotificationMessage message = new NotificationMessage(messageType.getSubject(), 
				templateEngineService.find(messageType.getEmailTemplateId(), parameters));
		return message;
	}
	
	@Override
	public NotificationMessage send(NotificationMessageType messageType,Map<String, Object> parameters, String receiver) {
		NotificationMessage message = new NotificationMessage(messageType.getSubject(), 
				templateEngineService.find(messageType.getEmailTemplateId(), parameters));
		
		MailNotificationEvent notificationEvent = new MailNotificationEvent(message,receiver);
		//mailEventService.fire(new MailNotificationEvent(message,receiver)); //TODO this one is better because asynchronous
		
		try {
			mailService.send(notificationEvent.getMessage(), notificationEvent.getReceiver());
		} catch (Exception e) {
			log.log(Level.WARNING, e.toString());
		}
		
		return message;
	}
	
	@Override
	public NotificationMessage send(NotificationMessageType messageType,Map<String, Object> parameters, Party receiver) {
		return send(messageType, parameters, receiver.getContact().getEmail());
	}

}
