package br.com.booktalks.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.booktalks.dto.LivroDto;
import br.com.booktalks.dto.PessoaDto;
import br.com.booktalks.entities.Avaliacao;
import br.com.booktalks.entities.Livro;
import br.com.booktalks.entities.Pessoa;
import br.com.booktalks.enums.Cargo;
import br.com.booktalks.messaging.RabbitMQProducer;
import br.com.booktalks.repositories.LivroRepository;
import br.com.booktalks.repositories.PessoaRepository;

@Service
public class LivroService {

	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	RabbitMQProducer mqProducer;
	
	
	@Autowired
	ModelMapper modelMapper;
	
	public LivroDto save(Livro livro){
		Pessoa pessoa = pessoaRepository.findById(livro.getAutor().getPessoa_id()).orElseThrow(()-> new IllegalArgumentException("Pessoa não existente na base de dados"));
		PessoaDto pessoaDto = new PessoaDto();
		pessoa.setCargo(Cargo.AUTOR);
		pessoaDto = modelMapper.map(pessoa, PessoaDto.class);
		livro.setAvaliacao(null);
		Livro livroSalvo = livroRepository.save(livro);
		LivroDto livroDto = modelMapper.map(livroSalvo, LivroDto.class);
		livroDto.setAutor(pessoaDto);
		
		try {
			mqProducer.sendEmailLivroPublicado(pessoa.getEmail(),"Livro Publicado", pessoa.getNome());
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return livroDto;
	}
	
	public List<LivroDto> findAll() {
		List<Livro> livro = livroRepository.findAll();
		List<LivroDto> livroDto = new ArrayList<>();
		
		for (Livro livroLista : livro) {
			LivroDto livroListaDto = modelMapper.map(livroLista, LivroDto.class);
			livroDto.add(livroListaDto);
		}	
		return livroDto;
	}
	
	public LivroDto findById(Integer id) {
		Livro livro = livroRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Pessoa não existente na base de dados"));
		return modelMapper.map(livro, LivroDto.class);
	}
	
	public List<LivroDto> findAllLivrosByPessoaId (Integer pessoaId){
		List<Livro> livros = livroRepository.findAlLivrosByPessoaId(pessoaId);
		List<LivroDto> livrosDto = new ArrayList<>();
		
		for (Livro livroLista : livros) {
			livrosDto.add(modelMapper.map(livroLista, LivroDto.class));
		}
		return livrosDto;	
	}
	
	public LivroDto update (Livro livro) {
		Livro livroBanco = livroRepository.findById(livro.getLivro_id()).orElseThrow(()-> new IllegalArgumentException("Livro não existente na base de dados"));
	
		if(livro.getAutor() == null) {
			livro.setAutor(livro.getAutor());
		}
		if(livro.getAvaliacao() == null) {
			livro.setAvaliacao(livroBanco.getAvaliacao());
		}
		if(livro.getCategoria() == null) {
			livro.setCategoria(livroBanco.getCategoria());
		}
		if(livro.getDescricao() == null) {
			livro.setDescricao(livroBanco.getDescricao());
		}
		if(livro.getISBN() == null) {
			livro.setISBN(livroBanco.getISBN());
		}
		if(livro.getNome() == null) {
			livro.setNome(livroBanco.getNome());
		}
		if(livro.getPaginas() == null) {
			livro.setPaginas(livroBanco.getPaginas());
		}
		if(livro.getPreco() == null) {
			livro.setPreco(livroBanco.getPreco());
		}
		livroRepository.save(livro);
		return modelMapper.map(livro, LivroDto.class);
	}

	
	public LivroDto delete (Integer id) {
		Livro livroDeletado = livroRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Livro não existente na base de dados"));
		PessoaDto pessoaDto = modelMapper.map(pessoaRepository.findById(livroDeletado.getAutor().getPessoa_id()), PessoaDto.class);
		LivroDto livroDeletadaDto = modelMapper.map(livroDeletado, LivroDto.class);
		
		if(livroDeletado != null) {
			livroRepository.deleteById(id);
			livroDeletadaDto.setAutor(pessoaDto);
			return livroDeletadaDto;
		}
		return null;
	}
}
