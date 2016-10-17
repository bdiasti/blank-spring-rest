package br.com.blank;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import br.com.blank.domain.Pessoa;
import br.com.blank.repository.PessoaRepository;
import br.com.blank.v1.controller.PessoaController;


public class PessoaControllerTest {
	@Mock
	private PessoaRepository pessoaRepository;
	
	@InjectMocks
	PessoaController pessoaController;

	private MockMvc mockMvc;
	
    @Before
    public void setUp() throws Exception {
    	MockitoAnnotations.initMocks(this);
    	mockMvc = standaloneSetup(pessoaController).build();
    }
    
    @Ignore
    public void testGetAllPessas() throws Exception {
    	when(pessoaRepository.findAll()).thenReturn(new ArrayList<Pessoa>());
    	mockMvc.perform(get("/v1/pessoa"))
    			.andExpect(status().isOk())
    			.andExpect(content().string("[]")); 
    }
}