package br.com.blank.v1.controller;

import java.net.URI;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponses;
import com.wordnik.swagger.annotations.ApiResponse;

import br.com.blank.domain.Pessoa;
import br.com.blank.dto.error.ErrorDetail;
import br.com.blank.repository.PessoaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping({"/v1/"})
public class PessoaController {
	
	private static final Logger logger = LoggerFactory.getLogger(PessoaController.class);
	
	@Inject
	PessoaRepository pessoaRepository;
	
	@RequestMapping(value="/pessoas", method=RequestMethod.POST)	
	public ResponseEntity<?> createPessoa(@Valid @RequestBody Pessoa pessoa) {
		
		pessoa = pessoaRepository.save(pessoa);
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newPessoalUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pessoa.getPessoaId()).toUri();
		responseHeaders.setLocation(newPessoalUri);
		
		logger.debug("Pessoa criada com sucesso..");
		
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value="/pessoas", method=RequestMethod.GET)
	public ResponseEntity<Iterable<Pessoa>> getPessoas(Pageable pageable) {
		
		Iterable<Pessoa> pessoas = pessoaRepository.findAll(pageable);
		
		logger.debug("Criando hyperlinks para getPessoas");
		for(Pessoa p : pessoas) {
			updatePessoaResourceWithLinks(p);
		}
		
		logger.debug("Busca executada com sucesso");
		return new ResponseEntity<>(pessoas, HttpStatus.OK);
	}

	@RequestMapping(value="/pessoas/{pessoaId}", method=RequestMethod.GET)
	public ResponseEntity<?> getPessoa(@PathVariable Long pessoaId) {
		
		logger.debug("Criando hyperlink para getPessoa");
		Pessoa p = pessoaRepository.findOne(pessoaId);
		updatePessoaResourceWithLinks(p);
		logger.debug("Busca executada com sucesso");
		return new ResponseEntity<> (p, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pessoas/{pessoasId}", method=RequestMethod.PUT)
	public ResponseEntity<?> atualizarPessoa(@RequestBody Pessoa pessoa, @PathVariable Long pessoasId) {

		Pessoa p = pessoaRepository.save(pessoa);
		logger.debug("Pessoa atualizada com sucesso");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/pessoas/{pessoasId}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deletarPessoa(@PathVariable Long pessoasId) {
		
		logger.debug("Pessoa deletada com sucesso");
		pessoaRepository.delete(pessoasId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private void updatePessoaResourceWithLinks(Pessoa pessoa) {
		pessoa.add(linkTo(methodOn(PessoaController.class).getPessoa(pessoa.getPessoaId())).slash(pessoa.getPessoaId()).withSelfRel());
		//pessoa.add(linkTo(methodOn(VoteController.class).getAllVotes(pessoa.getPessoaId())).withRel("votes"));
		//pessoa.add(linkTo(methodOn(ComputeResultController.class).computeResult(pessoa.getPessoaId())).withRel("compute-result"));
	}
	

}
