package br.com.booktalks.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.booktalks.dto.LivroDto;
import br.com.booktalks.dto.PessoaDto;
import br.com.booktalks.dto.PublicacaoDto;
import br.com.booktalks.entities.Livro;
import br.com.booktalks.entities.Pessoa;
import br.com.booktalks.entities.Publicacao;
import br.com.booktalks.repositories.PessoaRepository;
import br.com.booktalks.repositories.PublicacaoRepository;

@Service
public class PublicacaoService {

	@Autowired
	PublicacaoRepository publicacaoRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public PublicacaoDto save (Publicacao publicacao){
		Optional<Pessoa> pessoa = pessoaRepository.findById(publicacao.getPessoa().getPessoa_id());
		PessoaDto pessoaDto = new PessoaDto();
		pessoaDto = modelMapper.map(pessoa, PessoaDto.class);
		Publicacao publicacaoSalva = publicacaoRepository.save(publicacao);
		PublicacaoDto publicacaoDto = modelMapper.map(publicacaoSalva, PublicacaoDto.class);
		publicacaoDto.setPessoa(pessoaDto);
		return publicacaoDto;
	}	
	
	public List<PublicacaoDto> findAll() {
		List<Publicacao> publicacao = publicacaoRepository.findAll();
		List<PublicacaoDto> publicacaoDto = new ArrayList<>();
		
		for (Publicacao publicacaoLista : publicacao) {
			PublicacaoDto publicacaoListaDto = modelMapper.map(publicacaoLista, PublicacaoDto.class);
			publicacaoDto.add(publicacaoListaDto);
		}	
		return publicacaoDto;
	}
	
	public PublicacaoDto findById(Integer id) {
		Publicacao publicacao = publicacaoRepository.findById(id).orElse(null);
		if(publicacao == null) {
			return null;
		}
		return modelMapper.map(publicacao, PublicacaoDto.class);
	}
	
	public PublicacaoDto update (Publicacao publicacao) {
		Publicacao publicacaoBanco = publicacaoRepository.findById(publicacao.getPublicacao_id()).orElse(null);
		
		if(publicacaoBanco == null) {
			return null;
		}
		if(publicacao.getPessoa() == null) {
			publicacao.setPessoa(publicacaoBanco.getPessoa());
		}
		if(publicacao.getConteudo() == null) {
			publicacao.setConteudo(publicacaoBanco.getConteudo());
		}
		if(publicacao.getFavorito() == null) {
			publicacao.setFavorito(publicacaoBanco.getFavorito());
		}
		if(publicacao.getLikes() == null) {
			publicacao.setLikes(publicacaoBanco.getLikes());
		}
		if(publicacao.getRepublicado() == null) {
			publicacao.setRepublicado(publicacaoBanco.getRepublicado());
		}
		publicacaoRepository.save(publicacao);
		return modelMapper.map(publicacao, PublicacaoDto.class);
	}
	
	public PublicacaoDto delete (Integer id) {
		Publicacao publicacaoDeletada = publicacaoRepository.findById(id).orElse(null);
		PessoaDto pessoaDto = modelMapper.map(pessoaRepository.findById(publicacaoDeletada.getPessoa().getPessoa_id()), PessoaDto.class);
		PublicacaoDto publicacaoDeletadaDto = modelMapper.map(publicacaoDeletada, PublicacaoDto.class);
		
		if(publicacaoDeletada != null) {
			publicacaoRepository.deleteById(id);
			publicacaoDeletadaDto.setPessoa(pessoaDto);
			return publicacaoDeletadaDto;
		}
		return null;
	}
}
