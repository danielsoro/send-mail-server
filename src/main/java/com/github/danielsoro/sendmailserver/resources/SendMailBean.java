package com.github.danielsoro.sendmailserver.resources;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.github.danielsoro.sendmailserver.model.Email;

/**
 * @author Daniel Cunha (danielsoro@gmail.com)
 * 
 */
@RequestScoped
public class SendMailBean {

	@Resource(mappedName = "java:/mail/gmail")
	private Session session;

	public void send(@Observes Email email) {
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setSubject("TESTE", "UTF-8");

			Address[] internetAdress;

			if (email.getAddress() != null) {
				internetAdress = new Address[email.getAddress().size()];
				for (int i = 0; i < email.getAddress().size(); i++) {
					internetAdress[i] = new InternetAddress(email.getAddress()
							.get(i));
				}
				msg.setRecipients(RecipientType.TO, internetAdress);
			}
			msg.setText("GAY!! :-)", "UTF-8");
			msg.setHeader("Content-Type", "text/html;charset=UTF-8");
			Transport.send(msg);
		} catch (MessagingException e) {
			System.out.println(e);
		}
	}
}
