package com.pedro.anderson.silveira.lima.springboot.dtos;

import jakarta.validation.constraints.NotBlank;

public record ContactRecordDto(@NotBlank String name, @NotBlank String phone, @NotBlank String email) { 
	
}