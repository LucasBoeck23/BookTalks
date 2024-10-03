package br.com.booktalks.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.booktalks.dto.AvaliacaoDto;
import br.com.booktalks.entities.Avaliacao;
import br.com.booktalks.entities.Livro;
import br.com.booktalks.repositories.AvaliacaoRepository;
import br.com.booktalks.repositories.LivroRepository;

@Service
public class AvaliacaoService {

	@Autowired
	AvaliacaoRepository avaliacaoRepository;
	
	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public AvaliacaoDto avaliar (Avaliacao avaliacao, Integer livroId) {
		Livro livro = livroRepository.findById(livroId).orElse(null);
		
		if(livro != null) {
			avaliacao.setLivro(livro);
			avaliacao.setData(LocalDate.now());
			avaliacaoRepository.save(avaliacao);
		
			 double somaNotas = livro.getAvaliacoes().stream()
				        .mapToDouble(Avaliacao::getNota)
				        .sum();	
			 
			  int quantidadeAvaliacoes = livro.getAvaliacoes().size();
			  
			  double media = quantidadeAvaliacoes > 0 ? somaNotas / quantidadeAvaliacoes : 0.0;
			   
			  	livro.setAvaliacao(media);
			    livroRepository.save(livro);
			
			 return modelMapper.map(avaliacao, AvaliacaoDto.class);
		}
		throw new IllegalArgumentException("Livro não encontrado");
	}
	
	public List<AvaliacaoDto> findAllAvaliacoesByPessoaId (Integer pessoaId){
		List<Avaliacao> avaliacao = avaliacaoRepository.findAllAvaliacoesByPessoaId(pessoaId);
		List<AvaliacaoDto> avaliacaoDto = new ArrayList<>();
		for (Avaliacao avaliacaoLista : avaliacao) {
			avaliacaoDto.add(modelMapper.map(avaliacaoLista, AvaliacaoDto.class));
		}
		return avaliacaoDto;
	}
	
	public List<AvaliacaoDto> findAllAvaliacoesByLivroId (Integer livroId){
		List<Avaliacao> avaliacao = avaliacaoRepository.findAllAvaliacoesByLivroId(livroId);
		List<AvaliacaoDto> avaliacaoDto = new ArrayList<>();
		for (Avaliacao avaliacaoLista : avaliacao) {
			avaliacaoDto.add(modelMapper.map(avaliacaoLista, AvaliacaoDto.class));
		}
		return avaliacaoDto;
	}
	
	public AvaliacaoDto excluir (Integer Id) {
		Avaliacao avaliacao = avaliacaoRepository.findById(Id).orElse(null);
		
		if(avaliacao != null) {
			avaliacaoRepository.delete(avaliacao);
			return modelMapper.map(avaliacao, AvaliacaoDto.class);
		}
		
		 throw new IllegalArgumentException("Avaliação não encontrada");
	}
}
