package com.pedro.anderson.silveira.lima.springboot.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pedro.anderson.silveira.lima.springboot.models.ContactModel;


@Repository	
public interface IContactRepository extends JpaRepository<ContactModel, UUID>{

}