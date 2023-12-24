package com.pedro.anderson.silveira.lima.springboot.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pedro.anderson.silveira.lima.springboot.dtos.ContactRecordDto;
import com.pedro.anderson.silveira.lima.springboot.models.ContactModel;
import com.pedro.anderson.silveira.lima.springboot.repositories.IContactRepository;

@Service
public class ContactService implements IContactService{

	@Autowired
	IContactRepository contactRepository;
	
	@Override
	public ContactModel save(ContactModel contactModel) {
				
		ContactModel result = contactRepository.save(contactModel);
				
		return result;
	}

	@Override
	public List<ContactModel> getAll() {
		
		return contactRepository.findAll();		
		
	}

	@Override
	public Optional<ContactModel> get(UUID id) {
		
		Optional<ContactModel> result = contactRepository.findById(id);
		
		return result;
	}

	@Override
	public Optional<ContactModel> update(UUID id, ContactRecordDto contactRecordDto) {
		
		Optional<ContactModel> result = contactRepository.findById(id);
		
		return result;
	}

	@Override
	public Optional<ContactModel> delete(UUID id) {
		
		Optional<ContactModel> result = contactRepository.findById(id);
				
		return result;
	}

}