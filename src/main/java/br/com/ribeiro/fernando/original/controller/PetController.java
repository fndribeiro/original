package br.com.ribeiro.fernando.original.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ribeiro.fernando.original.exception.ResourceNotFoundException;
import br.com.ribeiro.fernando.original.model.Pet;
import br.com.ribeiro.fernando.original.service.PetService;

@RestController 
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/pet")
public class PetController {

	@Autowired
	private PetService petService;
	
	@GetMapping
	public List<Pet> listPet() {
		return petService.listPet();
	}
	
	@GetMapping("/{id}")
	public Pet listPetById(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
		return petService.listPetById(id);
	}
	
	@PostMapping
	public Pet createPet(@Valid @RequestBody Pet pet) throws ResourceNotFoundException {
		return petService.createPet(pet);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pet> updatePet(@PathVariable(value = "id") long id, @Valid @RequestBody Pet pet) throws ResourceNotFoundException {
		return petService.updatePet(id, pet);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePet(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
		return petService.deletePet(id);
	}
	
}
