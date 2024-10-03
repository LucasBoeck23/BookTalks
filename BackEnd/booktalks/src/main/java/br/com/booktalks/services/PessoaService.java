package br.com.booktalks.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.booktalks.dto.PessoaDto;
import br.com.booktalks.entities.Carrinho;
import br.com.booktalks.entities.Pessoa;
import br.com.booktalks.messaging.RabbitMQProducer;
import br.com.booktalks.repositories.CarrinhoRepository;
import br.com.booktalks.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	CarrinhoRepository carrinhoRepository;
	
	@Autowired 
	EnderecoService enderecoService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	RabbitMQProducer rabbitMQProducer;
	
	  public PessoaDto save(Pessoa pessoa) {
		  int IdadeUsuario = Period.between(pessoa.getDataNascimento(), LocalDate.now()).getYears();
		  if(IdadeUsuario<=13) {
			  throw new IllegalArgumentException("O Usuario deve ter mais de 13 anos");
		  }
		  
		  pessoa.setDataCriacao(LocalDate.now());
		  pessoaRepository.save(pessoa); 
		  
		  
		  Carrinho carrinho = new Carrinho();
		  carrinho.setPessoa(pessoa);
		  carrinhoRepository.save(carrinho);
		  
		  //EMAIL DE BEM VINDO
		  try {
			    rabbitMQProducer.sendEmailBoasVindas(pessoa.getEmail(), "Boas Vindas ao BookTalks!", pessoa.getNome());
			} catch (Exception e) {
			    System.out.println(e);
			}
		  
			 PessoaDto pessoaDto  = modelMapper.map(pessoa, PessoaDto.class);
		  return pessoaDto;
	    }
	
		public List<PessoaDto> findAll(){
		List<Pessoa> pessoa = pessoaRepository.findAll();
		List<PessoaDto> pessoaDto = new ArrayList<>();
		for (Pessoa pessoaLista : pessoa) {
			PessoaDto pessoaDtoLista = modelMapper.map(pessoaLista, PessoaDto.class);
			pessoaDto.add(pessoaDtoLista);
		}
			return pessoaDto;
		}
		
		public PessoaDto findById(Integer id) {
			Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Pessoa não existente na base de dados"));
			return modelMapper.map(pessoa,PessoaDto.class);
		}
		
		public PessoaDto update (Pessoa pessoa) {
			Pessoa pessoabanco = pessoaRepository.findById(pessoa.getPessoa_id()).orElseThrow(()-> new IllegalArgumentException("Pessoa não existente na base de dados"));
		
			if(pessoa.getCargo().equals(null)) {
				pessoa.setCargo(pessoabanco.getCargo());
			}
			if(pessoa.getDataNascimento() == null) {
				pessoa.setDataNascimento(pessoabanco.getDataNascimento());
			}
			if(pessoa.getEmail() == null) {
				pessoa.setEmail(pessoabanco.getEmail());
			}
			if(pessoa.getSenha() == null) {
				pessoa.setSenha(pessoabanco.getSenha());
			}
			if(pessoa.getNome() == null) {
				pessoa.setNome(pessoabanco.getNome());
			}
			if(pessoa.getSobrenome() == null){
				pessoa.setSobrenome(pessoabanco.getSobrenome());
			}
			if(pessoa.getNomeUsuario() == null){
				pessoa.setNomeUsuario(pessoabanco.getNomeUsuario());
			}
			pessoa.setDataCriacao(pessoabanco.getDataCriacao());
			pessoa.setEndereco(null);
			pessoa.setSeguidores(null);
			pessoa.setSeguindo(null);
			pessoaRepository.save(pessoa);
			PessoaDto pessoaDto = modelMapper.map(pessoa, PessoaDto.class);
			return pessoaDto;
		}
		
		public PessoaDto delete (Integer id) {
			Pessoa pessoaDeletada = pessoaRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Pessoa não existente na base de dados"));
			
			if(pessoaDeletada != null) {
				pessoaRepository.deleteById(id);
				PessoaDto pessoaDeletadaDto = modelMapper.map(pessoaDeletada, PessoaDto.class);
				return pessoaDeletadaDto;
			}
			return null;
		}
}
			
