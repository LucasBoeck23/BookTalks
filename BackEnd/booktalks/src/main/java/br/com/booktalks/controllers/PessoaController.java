package br.com.booktalks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<PessoaDto> post ( @RequestBody @Valid Pessoa pessoa){
		return new ResponseEntity<>(pessoaService.save(pessoa), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity< List<Pessoa>> findAll(){
		return new ResponseEntity<>(pessoaService.findAll(),HttpStatus.OK);
	}
	
}
