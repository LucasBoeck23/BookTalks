package br.com.booktalks.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.booktalks.dto.PessoaDto;
import br.com.booktalks.entities.Carrinho;
import br.com.booktalks.entities.Pessoa;
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
	ModelMapper modelMapper;
	
	  public PessoaDto save(Pessoa pessoa) {
		  pessoa.setDataCriacao(LocalDate.now());
		  Pessoa pessoaSalva = pessoaRepository.save(pessoa); 
		  PessoaDto pessoaDto  = modelMapper.map(pessoaSalva, PessoaDto.class);
		  
		  Carrinho carrinho = new Carrinho();
		  carrinho.setPessoa(pessoaSalva);
		  carrinhoRepository.save(carrinho);
		  
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
		
		public PessoaDto findById(Integer id){
			Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
			return modelMapper.map(pessoa,PessoaDto.class);
		}
		
		public PessoaDto update (Pessoa pessoa) {
			Pessoa pessoabanco = pessoaRepository.findById(pessoa.getPessoa_id()).orElse(null);
			
			if(pessoabanco == null) {
				return null;
			}
		
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
			Pessoa pessoaDeletada = pessoaRepository.findById(id).orElse(null);
			PessoaDto pessoaDeletadaDto = modelMapper.map(pessoaDeletada, PessoaDto.class);
			
			if(pessoaDeletada != null) {
				pessoaRepository.deleteById(id);
				return pessoaDeletadaDto;
			}
			return null;
		}
}
			
