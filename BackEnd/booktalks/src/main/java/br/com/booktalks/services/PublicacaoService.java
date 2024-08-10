package br.com.booktalks.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.booktalks.dto.CitacaoDto;
import br.com.booktalks.dto.LikeDto;
import br.com.booktalks.dto.PessoaDto;
import br.com.booktalks.dto.PublicacaoDto;
import br.com.booktalks.dto.RepublicadoDto;
import br.com.booktalks.entities.Citacao;
import br.com.booktalks.entities.Like;
import br.com.booktalks.entities.Pessoa;
import br.com.booktalks.entities.Publicacao;
import br.com.booktalks.entities.Republicado;
import br.com.booktalks.repositories.CitacaoRepository;
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
	CitacaoRepository citacaoRepository;
	
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
	
	//-----------Metodos extras de publicacao / like / Republicado / Citação / comentario /-------------------//
	
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
	
	public List<PessoaDto> findPessoaByPublicacao(Integer id){
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
	
	
	public PublicacaoDto like (Integer pessoaId, Integer postId) {
		Pessoa pessoa = pessoaRepository.findById(pessoaId).orElse(null);
		Publicacao publicacao = publicacaoRepository.findById(postId).orElse(null);
		Like Novolike = new Like();
		List<Like> likeBanco = likeReposirory.findAll();
		
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
				
				return modelMapper.map(publicacao, PublicacaoDto.class);
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
		
		return modelMapper.map(publicacao, PublicacaoDto.class);
		}
		return null;
	}
	
	//-------------Republicar-------------//
	
	public RepublicadoDto republicar (Integer pessoaId ,Integer publicacaoId) {
		Pessoa pessoa = pessoaRepository.findById(pessoaId).orElse(null);
		Publicacao publicacao = publicacaoRepository.findById(publicacaoId).orElse(null);
		List<Republicado> republicacoesPessoa = republicadoRepository.findRepublicacoesByPessoaId(pessoaId);
		
		for (Republicado republicadoLista : republicacoesPessoa) {
			if(republicadoLista.getPessoa().equals(pessoa) && republicadoLista.getPublicacao().equals(publicacao));
				republicadoRepository.delete(republicadoLista);
				
				pessoa.getRepublicados().remove(republicadoLista);
				pessoaRepository.save(pessoa);
				publicacao.getPessoasRepublicados().remove(republicadoLista);
				publicacao.setNumeroRepublicados(publicacao.getNumeroRepublicados()-1);
				publicacaoRepository.save(publicacao);
				
				
				return modelMapper.map(republicadoLista, RepublicadoDto.class);
			}

		if(pessoa != null && publicacao != null) {
			Republicado republicado = new Republicado();
			republicado.setPessoa(pessoa);
			republicado.setPublicacao(publicacao);
			publicacao.setNumeroRepublicados(publicacao.getNumeroRepublicados()+1);
			republicadoRepository.save(republicado);
			publicacaoRepository.save(publicacao);
			return modelMapper.map(republicado, RepublicadoDto.class);
		}
		
		return null;
	}
	
	//-------------Citação-------------//
	
	public CitacaoDto citar (Integer pessoaId ,Integer publicacaoId,Citacao citacao) {
		Pessoa pessoa = pessoaRepository.findById(pessoaId).orElse(null);
		Publicacao publicacao = publicacaoRepository.findById(publicacaoId).orElse(null);
		
		if(pessoa != null && publicacao != null) {
		citacao.setPessoa(pessoa);
		citacao.setPublicacao(publicacao);
		citacaoRepository.save(citacao);
		return modelMapper.map(citacao, CitacaoDto.class);
		}
		return null;
	}
	
	//------------------FIM------------------//
	
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
		if(publicacao.getPessoasCurtidas() == null) {
			publicacao.setPessoasCurtidas(publicacaoBanco.getPessoasCurtidas());
		}
		publicacao.setNumeroLikes(publicacaoBanco.getNumeroLikes());
		publicacao.setNumeroRepublicados(publicacaoBanco.getNumeroRepublicados());
		publicacao.setNumeroFavoritos(publicacaoBanco.getNumeroFavoritos());
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
