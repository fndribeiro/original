package br.com.ribeiro.fernando.original.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ribeiro.fernando.original.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
