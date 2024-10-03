package br.com.booktalks.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.booktalks.dto.FavoritoDto;
import br.com.booktalks.entities.Favorito;
import br.com.booktalks.entities.Pessoa;
import br.com.booktalks.entities.Publicacao;
import br.com.booktalks.repositories.FavoritoRepository;
import br.com.booktalks.repositories.PessoaRepository;
import br.com.booktalks.repositories.PublicacaoRepository;

@Service
public class FavoritoService {

	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	PublicacaoRepository publicacaoRepository;
	
	@Autowired
	FavoritoRepository favoritoRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public FavoritoDto favoritar(Integer publicacaoId, Integer pessoaId){
	Pessoa pessoa = pessoaRepository.findById(pessoaId).orElseThrow(()-> new IllegalArgumentException("Pessoa não existente na base de dados"));
	Publicacao publicacao = publicacaoRepository.findById(publicacaoId).orElseThrow(()-> new IllegalArgumentException("Publicação não existente na base de dados"));
	Favorito favorito = new Favorito();	
	List<Favorito> favoritoBanco = favoritoRepository.findAllFavoritosByPessoaId(pessoaId);
	
	for (Favorito favoritoLista : favoritoBanco) {
		if(favoritoLista.getPublicacao().equals(publicacao)) {
			favoritoRepository.delete(favoritoLista);
			
			pessoa.getFavoritados().remove(favoritoLista);		
			publicacao.getPessoasFavoritos().remove(favoritoLista);
			publicacao.setNumeroFavoritos(publicacao.getNumeroFavoritos() - 1);
			
			pessoaRepository.save(pessoa);
			publicacaoRepository.save(publicacao);
			
			return modelMapper.map(favoritoLista, FavoritoDto.class);
		}
	}
	
	if(pessoa != null && publicacao != null){
			favorito.setPessoa(pessoa);
			favorito.setPublicacao(publicacao);
			favoritoRepository.save(favorito);
			
			pessoa.getFavoritados().add(favorito);		
			publicacao.getPessoasFavoritos().add(favorito);
			publicacao.setNumeroFavoritos(publicacao.getNumeroFavoritos() + 1);
			
			pessoaRepository.save(pessoa);
			publicacaoRepository.save(publicacao);
			
			return modelMapper.map(favorito, FavoritoDto.class);
		}
		return null;
	}
	
	public List<FavoritoDto> findAll(){
		List<Favorito> favoritos = favoritoRepository.findAll();
		List<FavoritoDto> favoritoDto = new ArrayList<>();
		for (Favorito favoritoLista : favoritos) {
			favoritoDto.add(modelMapper.map(favoritoLista, FavoritoDto.class));
		}
		return favoritoDto;
	}
	public List<FavoritoDto> findAllFavoritosByPessoaId(Integer pessoaId){
		List<Favorito> favoritos = favoritoRepository.findAllFavoritosByPessoaId(pessoaId);
		List<FavoritoDto> favoritoDto = new ArrayList<>();
		for (Favorito favoritoLista : favoritos) {
			favoritoDto.add(modelMapper.map(favoritoLista, FavoritoDto.class));
		}
		return favoritoDto;
	}
}
