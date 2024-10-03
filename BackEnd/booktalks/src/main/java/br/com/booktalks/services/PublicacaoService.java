package br.com.booktalks.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.booktalks.dto.ComentarioDto;
import br.com.booktalks.dto.LikeDto;
import br.com.booktalks.dto.PessoaDto;
import br.com.booktalks.dto.PublicacaoDto;
import br.com.booktalks.dto.RepublicadoDto;
import br.com.booktalks.entities.Comentario;
import br.com.booktalks.entities.Like;
import br.com.booktalks.entities.Pessoa;
import br.com.booktalks.entities.Publicacao;
import br.com.booktalks.entities.Republicado;
import br.com.booktalks.repositories.ComentarioRepository;
import br.com.booktalks.repositories.FavoritoRepository;
import br.com.booktalks.repositories.LikeReposirory;
import br.com.booktalks.repositories.PessoaRepository;
import br.com.booktalks.repositories.PublicacaoRepository;
import br.com.booktalks.repositories.RepublicadoRepository;

@Service
public class PublicacaoService {

	@Autowired
	PublicacaoRepository publicacaoRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	LikeReposirory likeReposirory;

	@Autowired
	RepublicadoRepository republicadoRepository;
	
	@Autowired
	ComentarioRepository comentarioRepository;
	
	@Autowired
	FavoritoRepository favoritoRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public PublicacaoDto save (Publicacao publicacao){
		Pessoa pessoa = pessoaRepository.findById(publicacao.getPessoa().getPessoa_id()).orElseThrow(()-> new IllegalArgumentException("Pessoa não existente na base de dados"));
		PessoaDto pessoaDto = new PessoaDto();
		pessoaDto = modelMapper.map(pessoa, PessoaDto.class);
		
		publicacao.setDataPublicacao(LocalDate.now());
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
		Publicacao publicacao = publicacaoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Publicação não existente na base de dados"));
		return modelMapper.map(publicacao, PublicacaoDto.class);
	}
	
	public PublicacaoDto update (Publicacao publicacao) {
		Publicacao publicacaoBanco = publicacaoRepository.findById(publicacao.getPublicacao_id()).orElseThrow(()-> new IllegalArgumentException("Publicação não existente na base de dados"));
	
		if(publicacao.getPessoa() == null) {
			publicacao.setPessoa(publicacaoBanco.getPessoa());
		}
		if(publicacao.getConteudo() == null) {
			publicacao.setConteudo(publicacaoBanco.getConteudo());
		}
		if(publicacao.getPessoasCurtidas() == null) {
			publicacao.setPessoasCurtidas(publicacaoBanco.getPessoasCurtidas());
		}
		publicacao.setDataPublicacao(publicacaoBanco.getDataPublicacao());
		publicacao.setNumeroLikes(publicacaoBanco.getNumeroLikes());
		publicacao.setNumeroRepublicados(publicacaoBanco.getNumeroRepublicados());
		publicacao.setNumeroFavoritos(publicacaoBanco.getNumeroFavoritos());
		publicacaoRepository.save(publicacao);
		return modelMapper.map(publicacao, PublicacaoDto.class);
	}
	
	public PublicacaoDto delete (Integer id) {
		Publicacao publicacaoDeletada = publicacaoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Publicação não existente na base de dados"));
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
