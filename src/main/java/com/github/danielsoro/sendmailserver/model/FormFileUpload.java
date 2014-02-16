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
package com.github.danielsoro.sendmailserver.model;

import java.io.File;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

/**
 * @author Daniel Cunha (danielsoro@gmail.com)
 * 
 */
public class FormFileUpload {
	private String subject;
	private String text;
	private File file;

	public File getFile() {
		return file;
	}

	@FormParam("uploadedFile")
	@PartType(MediaType.APPLICATION_OCTET_STREAM)
	public void setFile(File file) {
		this.file = file;
	}

	public String getSubject() {
		return subject;
	}

	@FormParam("subject")
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	@FormParam("text")
	public void setText(String text) {
		this.text = text;
	}

}
