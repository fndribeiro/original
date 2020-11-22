package br.com.ribeiro.fernando.original.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.ribeiro.fernando.original.exception.ResourceNotFoundException;
import br.com.ribeiro.fernando.original.model.Pet;
import br.com.ribeiro.fernando.original.repository.PetRepository;

@Service
public final class PetService {
	
	@Autowired
	private PetRepository petRepository;

	public Pet createPet(Pet pet) throws ResourceNotFoundException {		
		return petRepository.save(pet);
	}
	
	public List<Pet> listPet() {
		return petRepository.findAll();
	}
	
	public Pet listPetById(long id) throws ResourceNotFoundException {
		return petRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com este id."));
	}
	
	public ResponseEntity<Pet> updatePet(long id, Pet pet) throws ResourceNotFoundException {		
		return petRepository.findById(id).map(mapper -> {
			mapper.setCastrado(pet.isCastrado());
			mapper.setNome(pet.getNome());
			mapper.setSexoPet(pet.getSexoPet());
			mapper.setTipoPet(pet.getTipoPet());
			mapper.setImageUrl(pet.getImageUrl());
			petRepository.save(mapper);
			return ResponseEntity.ok().body(mapper);
		}).orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com este id."));
	}
	
	public ResponseEntity<String> deletePet(long id) throws ResourceNotFoundException {		
		Pet pet = petRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com este id."));		
		petRepository.delete(pet);		
		return ResponseEntity.ok().body("Pet removido.");		
	}
	
}
