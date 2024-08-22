package br.com.booktalks.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.booktalks.dto.SeguidorDto;
import br.com.booktalks.entities.Pessoa;
import br.com.booktalks.entities.Seguidor;
import br.com.booktalks.repositories.PessoaRepository;
import br.com.booktalks.repositories.SeguidorRepository;

@Service
public class SeguidorService {

	@Autowired
	SeguidorRepository seguidorRepository;
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public SeguidorDto seguir (Integer pessoaId, Integer pessoaSeguidoraId) {
		Pessoa pessoa = pessoaRepository.findById(pessoaId).orElse(null);
		Pessoa pessoaSeguidora = pessoaRepository.findById(pessoaSeguidoraId).orElse(null);
		List<Seguidor> seguindoLista = seguidorRepository.findAllSeguindoByPessoaId(pessoaSeguidoraId);
		Seguidor seguidor = new Seguidor();		
		
		for (Seguidor seguindo : seguindoLista) {
			if(seguindo.getPessoa().equals(pessoa)){
				seguidorRepository.delete(seguindo);
				//remove 1 seguindo de quem solicitou e remover o seguidor de quem ele escolheu
				pessoa.getSeguidores().remove(seguindo);
				pessoa.setNumeroSeguidores(pessoa.getNumeroSeguidores() - 1);
				pessoaSeguidora.getSeguindo().remove(seguindo);
				pessoaSeguidora.setNumeroSeguindo(pessoaSeguidora.getNumeroSeguindo() - 1);
				
				pessoaRepository.save(pessoa);
				pessoaRepository.save(pessoaSeguidora);
				return modelMapper.map(seguindo, SeguidorDto.class);
			}
		}
		
		if(pessoa != null && pessoaSeguidora != null){
			//adicionar um seguidor em quem ele escolheu e adicionar seguindo no usuario
			
			
			seguidor.setPessoa(pessoa);
			seguidor.setSeguindo(pessoaSeguidora);
			seguidorRepository.save(seguidor);
			
			pessoa.getSeguidores().add(seguidor);
			pessoa.setNumeroSeguidores(pessoa.getNumeroSeguidores() + 1);
			pessoaSeguidora.getSeguindo().add(seguidor);
			pessoaSeguidora.setNumeroSeguindo(pessoaSeguidora.getNumeroSeguindo() + 1);
			
			pessoaRepository.save(pessoa);
			pessoaRepository.save(pessoaSeguidora);
			return modelMapper.map(seguidor, SeguidorDto.class);
		}
		return null;
	}
}