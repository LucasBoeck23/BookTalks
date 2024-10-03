package br.com.booktalks.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.booktalks.dto.EnderecoDto;
import br.com.booktalks.entities.Endereco;
import br.com.booktalks.repositories.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;
    
    @Autowired
    ModelMapper modelMapper;


	public EnderecoDto save(String cep, Endereco endereco) {
		try {
			Endereco enderecoViaCep = viaCepService.getCepInfo(cep);
			if (enderecoViaCep != null) {
				enderecoViaCep.setNumero(endereco.getNumero());
				enderecoViaCep.setComplemento(endereco.getComplemento());
				enderecoViaCep.setPessoa(endereco.getPessoa());
				enderecoViaCep.setEndereco_id(endereco.getEndereco_id());
				enderecoRepository.save(enderecoViaCep);
				EnderecoDto enderecoDto = modelMapper.map(enderecoViaCep, EnderecoDto.class);
				return enderecoDto;
			}
			
		} catch (Exception e) {
			throw new IllegalArgumentException("CEP não encontrado");
		}
		return null;
	}
	
	 public List<EnderecoDto> findAll() {
	List<Endereco> endereco = enderecoRepository.findAll();
	List<EnderecoDto> enderecoDto = new ArrayList<>();
	        for (Endereco enderecoLista : endereco) {
				EnderecoDto enderecoDtoLista = modelMapper.map(enderecoLista, EnderecoDto.class);
				enderecoDto.add(enderecoDtoLista);
			}
		return enderecoDto;
	    }

	 public EnderecoDto findById(Integer id) {
	Endereco endeco = enderecoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Endereço não encontrado ou não existente"));
	EnderecoDto enderecoDto = modelMapper.map(endeco, EnderecoDto.class);
	        return enderecoDto;
	    }
	 
    public EnderecoDto update(Endereco endereco) {
    	Endereco enderecoBanco = enderecoRepository.findById(endereco.getEndereco_id()).orElseThrow(()-> new IllegalArgumentException("Endereço não existente na base de dados"));
    	Endereco enderecoViaCep = viaCepService.getCepInfo(endereco.getCep());
        if(enderecoBanco == null && endereco.getPessoa() == null) {
        	throw new IllegalArgumentException("Pessoa e Endereços Inexistentes");
        }
        if (endereco.getCep() == null) {
			endereco.setCep(enderecoBanco.getCep());
		}
        if (endereco.getComplemento() == null) {
        	endereco.setComplemento(enderecoBanco.getComplemento());
        }
        if (endereco.getNumero() == null) {
        	endereco.setNumero(enderecoBanco.getNumero());
        }        
        enderecoViaCep.setCep(endereco.getCep());
        enderecoViaCep.setComplemento(endereco.getComplemento());
        enderecoViaCep.setNumero(endereco.getNumero());
        enderecoViaCep.setPessoa(enderecoBanco.getPessoa());
        enderecoViaCep.setEndereco_id(enderecoBanco.getEndereco_id());
        enderecoRepository.save(enderecoViaCep);
        EnderecoDto enderecoDto = modelMapper.map(enderecoViaCep, EnderecoDto.class);
    	return enderecoDto;
    }

    public EnderecoDto delete(Integer id) {
        if (enderecoRepository.existsById(id) == true) {
            Endereco enderecoDeletado = enderecoRepository.findById(id).orElse(null);
            EnderecoDto enderecoDtoDeletado = modelMapper.map(enderecoDeletado, EnderecoDto.class);
            
            try {
                enderecoRepository.deleteById(id);
                return enderecoDtoDeletado;
            } catch (Exception e) {
                System.out.println(e);
                return null;
            }
        } else {
        	throw new IllegalArgumentException("Endereço não encontrado ou não existente");
        }
    }

    public List<Endereco> findByCep(String cep) {
        List<Endereco> enderecosAll = enderecoRepository.findAll();
        List<Endereco> enderecosCep = new ArrayList<>();
        for (Endereco endereco : enderecosAll) {
            if (endereco.getCep().equals(cep)) {
                enderecosCep.add(endereco);
            }
        }
        return enderecosCep;
    }

}
