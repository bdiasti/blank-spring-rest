package br.com.blank.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;

@Entity
public class Pessoa extends ResourceSupport{

	@Id
	@GeneratedValue
	@Column(name="PESSOA_ID")
	private Long id;


	@Column(name="NOME")
	@NotEmpty
	private String nome;
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getPessoaId() {
		return id;
	}

	public void setPessoaId(Long id) {
		this.id = id;
	}
	

	
}
