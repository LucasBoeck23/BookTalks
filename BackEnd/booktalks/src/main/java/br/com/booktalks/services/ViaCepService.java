package br.com.booktalks.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.booktalks.entities.Endereco;

@Service
public class ViaCepService {
	
    private static final String VIACEP_URL = "https://viacep.com.br/ws/{cep}/json/";

    public Endereco getCepInfo(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        Endereco endereco = restTemplate.getForObject(VIACEP_URL, Endereco.class, cep);
        return endereco;
    }
}
