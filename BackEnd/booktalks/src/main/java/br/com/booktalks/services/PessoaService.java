package br.com.booktalks.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.booktalks.dto.PessoaDto;
import br.com.booktalks.entities.Endereco;
import br.com.booktalks.entities.Pessoa;
import br.com.booktalks.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired 
	EnderecoService enderecoService;
	
	@Autowired
	ModelMapper modelMapper;
	
	  public PessoaDto save(Pessoa pessoa) {
		  pessoaRepository.save(pessoa);
	        if (pessoa.getEndereco() != null) {
	            for (Endereco endereco : pessoa.getEndereco()) {
	                endereco.setPessoa(pessoa);
	                enderecoService.save(endereco.getCep(), endereco);
	            }
	        }
	        PessoaDto pessoaDto  = modelMapper.map(pessoa, PessoaDto.class);
	        return pessoaDto;
	    }
	
	public List<Pessoa> findAll(){
		return pessoaRepository.findAll();
	}
}
