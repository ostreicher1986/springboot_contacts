package com.pedro.anderson.silveira.lima.springboot.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.pedro.anderson.silveira.lima.springboot.dtos.ContactRecordDto;
import com.pedro.anderson.silveira.lima.springboot.models.ContactModel;

public interface IContactService {

	ContactModel save(ContactModel contactModel);
	
	List<ContactModel> getAll();
	
	Optional<ContactModel> get(UUID id);
	
	Optional<ContactModel> update(UUID id, ContactRecordDto contactRecordDto);
	
	Optional<ContactModel> delete(UUID id);
	
}