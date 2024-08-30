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

import br.com.booktalks.dto.AvaliacaoDto;
import br.com.booktalks.dto.LivroDto;
import br.com.booktalks.entities.Avaliacao;
import br.com.booktalks.entities.Livro;
import br.com.booktalks.services.AvaliacaoService;
import br.com.booktalks.services.LivroService;

@RestController
@RequestMapping("/livro")
public class LivroController {

	@Autowired
	LivroService livroService;

	@Autowired
	AvaliacaoService avaliacaoService;
	
	@PostMapping
	public ResponseEntity<LivroDto> post( @RequestBody Livro livro){
		return new ResponseEntity<>(livroService.save(livro), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<LivroDto>>findAll(){
		return new ResponseEntity<>(livroService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LivroDto> findById(@PathVariable Integer id){
		return new ResponseEntity<>(livroService.findById(id),HttpStatus.OK);
	}
	@GetMapping("/pessoa/{pessoaId}")
	public ResponseEntity<List<LivroDto>> findAllLivrosByPessoaId(@PathVariable Integer pessoaId){
		return new ResponseEntity<>(livroService.findAllLivrosByPessoaId(pessoaId),HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<LivroDto>update(@RequestBody Livro Livro){
		return new ResponseEntity<>(livroService.update(Livro), HttpStatus.OK);
	}
	//------------------avaliar-------------------//
	@PutMapping("/avaliar/{produtoId}")
	public ResponseEntity<AvaliacaoDto> avaliar (@RequestBody Avaliacao avaliacao, @PathVariable Integer produtoId){
		return new ResponseEntity<>(avaliacaoService.avaliar(avaliacao,produtoId), HttpStatus.OK);
	}
	
	@GetMapping("/avaliar/pessoa/{pessoaId}")
	public ResponseEntity<List<AvaliacaoDto>> findAllAvaliacoesByPessoaId (@PathVariable Integer pessoaId){
		return new ResponseEntity<>(avaliacaoService.findAllAvaliacoesByPessoaId(pessoaId), HttpStatus.OK);
	}
	@GetMapping("/avaliar/livro/{livroId}")
	public ResponseEntity<List<AvaliacaoDto>> findAllAvaliacoesByLivroId (@PathVariable Integer livroId){
		return new ResponseEntity<>(avaliacaoService.findAllAvaliacoesByLivroId(livroId), HttpStatus.OK);
	}
	//---------------Fim------------//
	
	@DeleteMapping("/{id}")
	public ResponseEntity<LivroDto>delete(@PathVariable Integer id){
		return new ResponseEntity<>(livroService.delete(id), HttpStatus.OK);
	}
	
}
