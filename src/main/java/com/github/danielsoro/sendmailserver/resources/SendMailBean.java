/**
 * Copyright (c) 2014 Daniel Cunha
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.danielsoro.sendmailserver.resources;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.MediaType;

import com.github.danielsoro.sendmailserver.model.Email;

/**
 * @author Daniel Cunha (danielsoro@gmail.com)
 * 
 */
@RequestScoped
public class SendMailBean {

	@Resource(mappedName = "java:/mail/gmail")
	private Session session;

	@Inject
	@Named("default.encoding")
	private String defaultEncoding;

	@Asynchronous
	public void send(@Observes Email email) {
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setHeader("Content-Type", "text/html;charset=UTF-8");
			msg.setSubject(email.getSubject(), defaultEncoding);
			msg.setContent(email.getBody(), MediaType.TEXT_HTML);

			Address[] internetAdress;
			if (email.getAddress() != null) {
				internetAdress = new Address[email.getAddress().size()];
				for (int i = 0; i < email.getAddress().size(); i++) {
					internetAdress[i] = new InternetAddress(email.getAddress()
							.get(i));
				}
				msg.setRecipients(RecipientType.TO, internetAdress);
			}
			
			Transport.send(msg);
		} catch (MessagingException e) {
			System.out.println(e);
		}
	}
}
