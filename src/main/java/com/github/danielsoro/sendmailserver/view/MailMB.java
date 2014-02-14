package com.github.danielsoro.sendmailserver.view;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.github.danielsoro.sendmailserver.model.Email;

/**
 * @author Daniel Cunha (danielsoro@gmail.com)
 * 
 */
@Path("/mail")
public class MailMB {

	@Inject
	@Any
	private Event<Email> event;

	@GET
	@Path("/send")
	public Response send() {
		Email email = new Email("email1", "email2", "email3"); 
		event.fire(email);
		return Response.status(Status.OK).encoding("UTF-8")
				.entity("Hello World").build();
	}
}
