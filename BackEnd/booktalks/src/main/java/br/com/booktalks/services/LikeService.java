package br.com.booktalks.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.booktalks.dto.LikeDto;
import br.com.booktalks.dto.PessoaDto;
import br.com.booktalks.dto.PublicacaoDto;
import br.com.booktalks.entities.Like;
import br.com.booktalks.entities.Pessoa;
import br.com.booktalks.entities.Publicacao;
import br.com.booktalks.messaging.RabbitMQProducer;
import br.com.booktalks.repositories.LikeReposirory;
import br.com.booktalks.repositories.PessoaRepository;
import br.com.booktalks.repositories.PublicacaoRepository;

@Service
public class LikeService {
	
	@Autowired
	LikeReposirory likeReposirory;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	PublicacaoRepository publicacaoRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;

	
	public List<LikeDto> findAllLike() {
		List<Like> like = likeReposirory.findAll();
		List<LikeDto> likeDto = new ArrayList<>();
		for (Like likeLista : like) {
			LikeDto likeDtoLista = modelMapper.map(likeLista, LikeDto.class);
			likeDto.add(likeDtoLista);
		}
		return likeDto;
	}
	
	public List<PublicacaoDto> findPublicacaoCurtidaByPessoa (Integer id){

		List<Publicacao> publicacao = likeReposirory.findPublicacoesByPessoaId(id);
		List<PublicacaoDto> publicacaoDto = new ArrayList<>();
		for (Publicacao publicacaoLista : publicacao) {
			PublicacaoDto publicacaoDtoLista = modelMapper.map(publicacaoLista, PublicacaoDto.class);
			publicacaoDto.add(publicacaoDtoLista);
		}
		return publicacaoDto;
	}
	
	public List<PessoaDto> findPessoaByLikePublicacao(Integer id){
		List<Pessoa> pessoa = likeReposirory.findPessoasByPublicacaoId(id);
		List<PessoaDto> pessoaDto = new ArrayList<>()
;
		for (Pessoa pessoaLista : pessoa) {
			PessoaDto pessoaDtoLista = modelMapper.map(pessoaLista, PessoaDto.class);
			pessoaDto.add(pessoaDtoLista);
		}
		return pessoaDto;
	}
	
	public List<PublicacaoDto> findAllPublicacaoByPessoa(Integer id){
		List<Publicacao>publicacao = publicacaoRepository.findAllPublicacoesByPessoaId(id);
		List<PublicacaoDto> publicacaoDto = new ArrayList<>();
		for (Publicacao publicacaoLista: publicacao) {
			PublicacaoDto publicacaoDtoLista = modelMapper.map(publicacaoLista, PublicacaoDto.class);
			publicacaoDto.add(publicacaoDtoLista);
		}
		return publicacaoDto;
	}
	
	
	public LikeDto like (Integer pessoaId, Integer postId) {
		Pessoa pessoa = pessoaRepository.findById(pessoaId).orElseThrow(()-> new IllegalArgumentException("Pessoa não existente na base de dados"));
		Publicacao publicacao = publicacaoRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("Publicação não existente na base de dados"));
		Like Novolike = new Like();
		List<Like> likeBanco = likeReposirory.findAllLikesByPessoaId(pessoaId);
		
		for (Like like : likeBanco) {
			if(like.getPessoa().equals(pessoa) && like.getPublicacao().equals(publicacao)) {
				likeReposirory.delete(like);
				
				List<Like>pessoaCurtidas = pessoa.getCurtidas();
				pessoaCurtidas.remove(like);
				pessoa.setCurtidas(pessoaCurtidas);
				pessoaRepository.save(pessoa);
				
				List<Like>pessoasPublicacaoCurtidas = publicacao.getPessoasCurtidas();
				pessoasPublicacaoCurtidas.remove(like);
				publicacao.setPessoasCurtidas(pessoasPublicacaoCurtidas);
				publicacao.setNumeroLikes(publicacao.getNumeroLikes()-1);
				publicacaoRepository.save(publicacao);
				
				
				return modelMapper.map(like, LikeDto.class);
			}
		}
		
		
		if(pessoa != null && publicacao != null) {
			Novolike.setPessoa(pessoa);
			Novolike.setPublicacao(publicacao);
			likeReposirory.save(Novolike);
			
		List<Like>pessoaCurtidas = pessoa.getCurtidas();
		pessoaCurtidas.add(Novolike);
		pessoa.setCurtidas(pessoaCurtidas);
		pessoaRepository.save(pessoa);
		
		List<Like>pessoasPublicacaoCurtidas = publicacao.getPessoasCurtidas();
		pessoasPublicacaoCurtidas.add(Novolike);
		publicacao.setPessoasCurtidas(pessoasPublicacaoCurtidas);
		publicacao.setNumeroLikes(publicacao.getNumeroLikes()+1);
		publicacaoRepository.save(publicacao);
		
		return modelMapper.map(Novolike, LikeDto.class);
		}
		return null;
	}
	
}
