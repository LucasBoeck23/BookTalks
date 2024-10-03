package br.com.booktalks.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.booktalks.dto.PessoaDto;
import br.com.booktalks.dto.RepublicadoDto;
import br.com.booktalks.entities.Pessoa;
import br.com.booktalks.entities.Publicacao;
import br.com.booktalks.entities.Republicado;
import br.com.booktalks.repositories.PessoaRepository;
import br.com.booktalks.repositories.PublicacaoRepository;
import br.com.booktalks.repositories.RepublicadoRepository;

@Service
public class RepublicadoService {

	@Autowired
	RepublicadoRepository republicadoRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	PublicacaoRepository publicacaoRepository;
	
	public RepublicadoDto republicar (Integer pessoaId ,Integer publicacaoId) {
		Pessoa pessoa = pessoaRepository.findById(pessoaId).orElseThrow(()-> new IllegalArgumentException("Pessoa não existente na base de dados"));
		Publicacao publicacao = publicacaoRepository.findById(publicacaoId).orElseThrow(()-> new IllegalArgumentException("Publicação não existente na base de dados"));
		List<Republicado> republicacoesPessoa = republicadoRepository.findRepublicacoesByPessoaId(pessoaId);
		Republicado novoRepublicado = new Republicado();
		
		for (Republicado republicadoLista : republicacoesPessoa) {
			if(republicadoLista.getPublicacao().equals(publicacao)) {
				republicadoRepository.delete(republicadoLista);
				
				pessoa.getRepublicados().remove(republicadoLista);
				pessoaRepository.save(pessoa);
				publicacao.getPessoasRepublicados().remove(republicadoLista);
				publicacao.setNumeroRepublicados(publicacao.getNumeroRepublicados()-1);
				publicacaoRepository.save(publicacao);
				return modelMapper.map(republicadoLista, RepublicadoDto.class);
			}
		}

		if(pessoa != null && publicacao != null) {
			novoRepublicado.setPessoa(pessoa);
			novoRepublicado.setPublicacao(publicacao);
			publicacao.setNumeroRepublicados(publicacao.getNumeroRepublicados()+1);
			republicadoRepository.save(novoRepublicado);
			publicacaoRepository.save(publicacao);
			return modelMapper.map(novoRepublicado, RepublicadoDto.class);
		}
		
		return null;
	}
	
	public List<RepublicadoDto> findAllRepublicados(){
		List<Republicado> republicado = republicadoRepository.findAll();
		List<RepublicadoDto> republicadoDto = new ArrayList<>();
		for (Republicado republicadoLista : republicado) {
		republicadoDto.add(modelMapper.map(republicadoLista, RepublicadoDto.class));
		}
		return republicadoDto;
	}
	
	public List<PessoaDto> findAllPessoaByRepublicados(Integer id){
		List<Pessoa> pessoa = republicadoRepository.findPessoasByRepublicacaoId(id);
		List<PessoaDto> pessoaDto = new ArrayList<>();
		for (Pessoa pessoaLista : pessoa) {
			pessoaDto.add(modelMapper.map(pessoaLista, PessoaDto.class));
		}
		return pessoaDto;
	}
	
	public List<RepublicadoDto> findRepublicacoesByPessoaId(Integer id){
		List<Republicado> republicados = republicadoRepository.findRepublicacoesByPessoaId(id);
		List<RepublicadoDto> republicadoDto = new ArrayList<>();
		for (Republicado republicado : republicados) {
			RepublicadoDto republicadoDtoLista = modelMapper.map(republicado, RepublicadoDto.class);
			republicadoDto.add(republicadoDtoLista);
		}
		return republicadoDto;
	}
}
