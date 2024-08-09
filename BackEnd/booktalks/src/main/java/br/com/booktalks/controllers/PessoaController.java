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

import br.com.booktalks.dto.LikeDto;
import br.com.booktalks.dto.PessoaDto;
import br.com.booktalks.entities.Pessoa;
import br.com.booktalks.services.PessoaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	PessoaService pessoaService;
	
	@PostMapping
	public ResponseEntity<PessoaDto> post( @RequestBody @Valid Pessoa pessoa){
		return new ResponseEntity<>(pessoaService.save(pessoa), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<PessoaDto>>findAll(){
		return new ResponseEntity<>(pessoaService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PessoaDto> findById(@PathVariable Integer id){
		return new ResponseEntity<>(pessoaService.findById(id),HttpStatus.OK);
	}
	@PutMapping
	public ResponseEntity<PessoaDto>update(@RequestBody Pessoa pessoa){
		return new ResponseEntity<>(pessoaService.update(pessoa), HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<PessoaDto>delete(@PathVariable Integer id){
		return new ResponseEntity<>(pessoaService.delete(id), HttpStatus.OK);
	}
	
}
