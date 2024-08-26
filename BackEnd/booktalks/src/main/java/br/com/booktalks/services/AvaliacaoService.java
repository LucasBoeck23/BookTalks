package br.com.booktalks.services;

import java.time.LocalDate;
import java.util.ArrayList;

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
		
		return null;
	}
	
	public AvaliacaoDto excluir (Integer Id) {
		Avaliacao avaliacao = avaliacaoRepository.findById(Id).orElse(null);
		
		if(avaliacao != null) {
			avaliacaoRepository.delete(avaliacao);
			return modelMapper.map(avaliacao, AvaliacaoDto.class);
		}
		
		return null;
	}
}
