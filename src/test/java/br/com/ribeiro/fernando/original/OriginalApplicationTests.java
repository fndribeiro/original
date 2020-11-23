package br.com.ribeiro.fernando.original;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import br.com.ribeiro.fernando.original.exception.ResourceNotFoundException;
import br.com.ribeiro.fernando.original.model.Pet;
import br.com.ribeiro.fernando.original.model.SexoPet;
import br.com.ribeiro.fernando.original.model.TipoPet;
import br.com.ribeiro.fernando.original.repository.PetRepository;
import br.com.ribeiro.fernando.original.service.PetService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OriginalApplication.class)
class OriginalApplicationTests {

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private PetService petService;

	private ClientAndServer mockServer;

	@Test
	void savePet() {

		Pet pet = new Pet();
		pet.setCastrado(true);
		pet.setImageUrl("");
		pet.setNome("Leonardo");
		pet.setSexoPet(SexoPet.M);
		pet.setTipoPet(TipoPet.Gato);

		petRepository.save(pet);

		List<Pet> findAll = petRepository.findAll();

		Assert.notEmpty(findAll, "Lista não pode estar vazia.");

	}

	@Test
	void findPetById() {

		Pet gato = new Pet();
		gato.setCastrado(true);
		gato.setImageUrl("");
		gato.setNome("Leonardo");
		gato.setSexoPet(SexoPet.M);
		gato.setTipoPet(TipoPet.Gato);

		Pet cachorro = new Pet();
		cachorro.setCastrado(false);
		cachorro.setImageUrl("");
		cachorro.setNome("Belinha");
		cachorro.setSexoPet(SexoPet.F);
		cachorro.setTipoPet(TipoPet.Cachorro);

		petRepository.save(cachorro);

		List<Pet> findAll = petRepository.findAll();

		List<Long> idList = new ArrayList<Long>();

		findAll.forEach(pet -> {
			idList.add(pet.getId());
		});

		Optional<Pet> petFound = petRepository.findById(idList.get(0));

		Assert.hasText(petFound.get().getNome(), "Nome não pode ser vazio.");
	}

	@Test
	void serviceLayer() throws ResourceNotFoundException {

		Pet pet = new Pet();
		pet.setCastrado(true);
		pet.setImageUrl("");
		pet.setNome("Leonardo");
		pet.setSexoPet(SexoPet.M);
		pet.setTipoPet(TipoPet.Gato);

		petService.createPet(pet);

		Pet foundPet = petService.listPetById(pet.getId());

		Assert.isTrue(foundPet.getNome().equals(pet.getNome()), "Nome deve ser igual.");
	}

	@Test
	void mockClientApi() {
		
		mockServer = ClientAndServer.startClientAndServer(1080);
		
		HttpRequest request = new HttpRequest();
		org.mockserver.model.HttpResponse response = new org.mockserver.model.HttpResponse();

		new MockServerClient("127.0.0.1", 1080)
				.when(request.withMethod("POST").withPath("/api").withHeader("\"Content-type\", \"application/json\"")
						.withBody("{username: 'teste', password: 'teste'}"))
				.respond(response.withStatusCode(200)
						.withHeader(new Header("Content-Type", "application/json; charset=utf-8"))
						.withBody("{ token: 'dstT45f4s8TEopgK' }"));
		
		mockServer.stop();
	}

}
