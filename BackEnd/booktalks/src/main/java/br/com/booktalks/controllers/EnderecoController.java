package br.com.booktalks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.booktalks.dto.EnderecoDto;
import br.com.booktalks.dto.PessoaDto;
import br.com.booktalks.entities.Endereco;
import br.com.booktalks.services.EnderecoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;
	
	@PostMapping
	public ResponseEntity<EnderecoDto> post( @RequestBody Endereco endereco){
		return new ResponseEntity<>(enderecoService.save(endereco.getCep(), endereco), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity< List<EnderecoDto>>findAll(){
		return new ResponseEntity<>(enderecoService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EnderecoDto> findById(@PathVariable Integer id){
		return new ResponseEntity<>(enderecoService.findById(id),HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<EnderecoDto>update(@RequestBody Endereco endereco){
		return new ResponseEntity<>(enderecoService.update(endereco), HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<EnderecoDto>delete(@PathVariable Integer id){
		return new ResponseEntity<>(enderecoService.delete(id), HttpStatus.OK);
	}
	
}
