package br.com.booktalks.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.booktalks.dto.ComentarioDto;
import br.com.booktalks.entities.Comentario;
import br.com.booktalks.entities.Pessoa;
import br.com.booktalks.entities.Publicacao;
import br.com.booktalks.repositories.ComentarioRepository;
import br.com.booktalks.repositories.PessoaRepository;
import br.com.booktalks.repositories.PublicacaoRepository;

@Service
public class ComentarioService {
	
	@Autowired
	ComentarioRepository comentarioRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	PublicacaoRepository publicacaoRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public ComentarioDto comentar(Integer pessoaId , Integer publicacaoId, Comentario comentario) {
		Pessoa pessoa = pessoaRepository.findById(pessoaId).orElse(null);
		Publicacao publicacao = publicacaoRepository.findById(publicacaoId).orElse(null);
		
		if(pessoa == null) {
			throw new IllegalArgumentException("Pessoa nao existente");
		} else if(publicacao == null) {
			throw new IllegalArgumentException("Publicação nao existente");
		}
		
		comentario.setPessoa(pessoa);
		comentario.setPublicacao(publicacao);
		
		pessoa.getComentarios().add(comentario);
		publicacao.getPessoasComentarios().add(comentario);
		publicacao.setNumeroComentarios(publicacao.getNumeroComentarios() + 1);
		
		comentarioRepository.save(comentario);
		ComentarioDto comentarioDto = modelMapper.map(comentario, ComentarioDto.class);
		pessoaRepository.save(pessoa);
		publicacaoRepository.save(publicacao);
		
		return comentarioDto;
	}
	
	public ComentarioDto excluirComentario (Integer comentarioId){
		Optional<Comentario> comentario = comentarioRepository.findById(comentarioId);
		if(comentario.isPresent()) {
			comentarioRepository.deleteById(comentarioId);
			return modelMapper.map(comentario, ComentarioDto.class);
		} else {
			throw new IllegalArgumentException("Comentario não existente");
		}
	}
	
	public List<ComentarioDto> findAllComentarios(){
	List<Comentario> comentario = comentarioRepository.findAll();
	List<ComentarioDto> comentarioDto = new ArrayList<>();
	
	for (Comentario comentarioLista : comentario) {
		ComentarioDto comentarioDtoLista = modelMapper.map(comentarioLista, ComentarioDto.class);
		comentarioDto.add(comentarioDtoLista);
	}
		return comentarioDto;
	}
	
	public List<ComentarioDto> findComentariosByPessoaId (Integer pessoaId){
		List<Comentario> comentario = comentarioRepository.findComentariosByPessoaId(pessoaId);
		List<ComentarioDto> comentarioDto = new ArrayList<>();
		
		for (Comentario comentarioLista : comentario) {
			comentarioDto.add(modelMapper.map(comentarioLista, ComentarioDto.class));
		}
		return comentarioDto;
	}
	public List<ComentarioDto> findComentariosByPublicacaoId (Integer publicacaoId){
		List<Comentario> comentario = comentarioRepository.findComentariosByPublicacaoId(publicacaoId);
		List<ComentarioDto> comentarioDto = new ArrayList<>();
		
		for (Comentario comentarioLista : comentario) {
			comentarioDto.add(modelMapper.map(comentarioLista, ComentarioDto.class));
		}
		return comentarioDto;
	}

}
