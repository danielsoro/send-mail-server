package com.github.danielsoro.sendmailserver.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Cunha (danielsoro@gmail.com)
 * 
 */
public class Email {
	private List<String> address;

	public Email(String... addresses) {
		List<String> emails = new ArrayList<String>();
		for (String address : addresses) {
			emails.add(address);
		}
		setAddress(emails);
	}

	public List<String> getAddress() {
		return address;
	}

	public void setAddress(List<String> address) {
		this.address = address;
	}

}
