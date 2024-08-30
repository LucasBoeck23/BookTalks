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

import br.com.booktalks.dto.ComentarioDto;
import br.com.booktalks.dto.LikeDto;
import br.com.booktalks.dto.PessoaDto;
import br.com.booktalks.dto.PublicacaoDto;
import br.com.booktalks.dto.RepublicadoDto;
import br.com.booktalks.entities.Comentario;
import br.com.booktalks.entities.Publicacao;
import br.com.booktalks.services.PublicacaoService;

@RestController
@RequestMapping("publicacao")
public class PublicacaoController {

	@Autowired
	PublicacaoService publicacaoService;
	
	@PostMapping
	public ResponseEntity<PublicacaoDto> post( @RequestBody Publicacao publicacao){
		return new ResponseEntity<>(publicacaoService.save(publicacao), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<PublicacaoDto>>findAll(){
		return new ResponseEntity<>(publicacaoService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PublicacaoDto> findById(@PathVariable Integer id){
		return new ResponseEntity<>(publicacaoService.findById(id),HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<PublicacaoDto>update(@RequestBody Publicacao publicacao){
		return new ResponseEntity<>(publicacaoService.update(publicacao), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PublicacaoDto>delete(@PathVariable Integer id){
		return new ResponseEntity<>(publicacaoService.delete(id), HttpStatus.OK);
	}
}
