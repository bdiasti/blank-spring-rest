package br.com.blank.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.blank.domain.Pessoa;

public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Long> {
	
	@Cacheable(value="buscarPessoa", key="#name")
	List<Pessoa> findByNome(String nome);
	
}