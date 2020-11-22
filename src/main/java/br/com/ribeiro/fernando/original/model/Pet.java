package br.com.ribeiro.fernando.original.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity
public class Pet {

	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	@Size(min = 3, max = 15)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private SexoPet sexoPet;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private TipoPet tipoPet;
	
	@NotNull
	private boolean castrado;
	
	@NotNull
	private String imageUrl;

	public Pet() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public SexoPet getSexoPet() {
		return sexoPet;
	}

	public void setSexoPet(SexoPet sexoPet) {
		this.sexoPet = sexoPet;
	}

	public TipoPet getTipoPet() {
		return tipoPet;
	}

	public void setTipoPet(TipoPet tipoPet) {
		this.tipoPet = tipoPet;
	}

	public boolean isCastrado() {
		return castrado;
	}

	public void setCastrado(boolean castrado) {
		this.castrado = castrado;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
