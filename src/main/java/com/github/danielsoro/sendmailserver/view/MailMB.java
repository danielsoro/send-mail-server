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

package com.github.danielsoro.sendmailserver.view;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import com.github.danielsoro.sendmailserver.model.Email;
import com.github.danielsoro.sendmailserver.model.FormFileUpload;

/**
 * @author Daniel Cunha (danielsoro@gmail.com)
 * 
 */
@Path("mail")
public class MailMB {

	@Inject
	@Any
	private Event<Email> event;

	@Inject
	@Named("default.encoding")
	private String defautlEncoding;

	@POST
	@Path("send")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response send(@MultipartForm FormFileUpload fileUpload) {
		try {
			FileReader fileReader = new FileReader(fileUpload.getFile());
			Email email = new Email(fileUpload.getSubject(),
					fileUpload.getText(), "danielsoro@gmail.com");
			event.fire(email);
			return null;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
