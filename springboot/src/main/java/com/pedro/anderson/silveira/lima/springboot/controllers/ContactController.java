package com.pedro.anderson.silveira.lima.springboot.controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.pedro.anderson.silveira.lima.springboot.dtos.ContactRecordDto;
import com.pedro.anderson.silveira.lima.springboot.models.ContactModel;
import com.pedro.anderson.silveira.lima.springboot.services.IContactService;
import jakarta.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ContactController {

	@Autowired
	IContactService contactService;
	
	@PostMapping("/contacts")
	public ResponseEntity<Object> save(@RequestBody @Valid ContactRecordDto contactRecordDto){
		
		var contactModel = new ContactModel();
		
		BeanUtils.copyProperties(contactRecordDto, contactModel);
		
		var result = contactService.save(contactModel);
		
		if ( result != null )
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		
	}
	
	@GetMapping("/contacts")
	public ResponseEntity<List<ContactModel>> getAll(){
		
		var result = contactService.getAll();
		
		if(!result.isEmpty()) {
			
			for(ContactModel contact : result) {
			
				UUID id = contact.getId();
				
				contact.add(linkTo(methodOn(ContactController.class).get(id)).withSelfRel());
			}
			
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
		
	}
	
	@GetMapping("/contacts/{id}")
	public ResponseEntity<Object> get(@PathVariable(value="id") UUID id){
		
		var result = contactService.get(id);
		
		if(result.isEmpty()) 
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found.");
		
		result.get().add(linkTo(methodOn(ContactController.class).getAll()).withRel("Contacts List"));
		
		return ResponseEntity.status(HttpStatus.OK).body(result.get());
		
	}
	
	@PutMapping("/contacts/{id}")
	public ResponseEntity<Object> update(@PathVariable(value="id") UUID id, @RequestBody @Valid ContactRecordDto contactRecordDto){
		
		var result = contactService.update(id, contactRecordDto);
		
		if(result.isEmpty()) 
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found.");
		
		var contactModel = result.get();
		
		BeanUtils.copyProperties(contactRecordDto, contactModel);
		
		return ResponseEntity.status(HttpStatus.OK).body(contactModel);
		
	}
	
	@DeleteMapping("/contacts/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value="id") UUID id){
		
		var result = contactService.delete(id);
		
		if(result.isEmpty()) 
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found.");
		
		
		return ResponseEntity.status(HttpStatus.OK).body("Contact deleted successfully.");
		
	}
	
}