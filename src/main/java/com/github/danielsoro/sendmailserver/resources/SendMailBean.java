package com.github.danielsoro.sendmailserver.resources.impl;

import javax.annotation.Resource;
import javax.enterprise.event.Observes;
import javax.mail.Session;

import com.github.danielsoro.sendmailserver.model.Email;
import com.github.danielsoro.sendmailserver.resources.SendMsg;

/**
 * @author Daniel Cunha (danielsoro@gmail.com)
 * 
 */
public class SendMail implements SendMsg {

	@Resource(mappedName = "java:jboss/mail/Gmail")
	private Session session;

	public void send(@Observes Email email) {
		// TODO Implementar método.
		throw new UnsupportedOperationException();
	}

}
