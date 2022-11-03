package com.gdu.contact.service;

import java.util.List;

import com.gdu.contact.domain.ContactDTO;

public interface ContactService {
	public List<ContactDTO> findAllContacts();
}
