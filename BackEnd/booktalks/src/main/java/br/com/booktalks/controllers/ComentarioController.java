package br.com.booktalks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.booktalks.dto.ComentarioDto;
import br.com.booktalks.entities.Comentario;
import br.com.booktalks.services.ComentarioService;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
	
	@Autowired
	ComentarioService comentarioService;
	
	@PutMapping("/comentar/{pessoaId}/{publicacaoId}")
	public ResponseEntity<ComentarioDto> comentar ( @PathVariable Integer pessoaId , @PathVariable Integer publicacaoId, @RequestBody Comentario comentario){
		return new ResponseEntity<>(comentarioService.comentar(pessoaId, publicacaoId, comentario), HttpStatus.OK);
	}
	
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<ComentarioDto> excluir (@PathVariable Integer id){
		return new ResponseEntity<>(comentarioService.excluirComentario(id), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<ComentarioDto>> findAllComentarios(){
		return new ResponseEntity<>(comentarioService.findAllComentarios(), HttpStatus.OK);
	}
	@GetMapping("/pessoa/{pessoaId}")
	public ResponseEntity<List<ComentarioDto>> findComentariosByPessoaId(@PathVariable Integer pessoaId){
		return new ResponseEntity<>(comentarioService.findComentariosByPessoaId(pessoaId), HttpStatus.OK);
	}
	@GetMapping("/publicacao/{publicacaoId}")
	public ResponseEntity<List<ComentarioDto>> findComentariosByPublicacaoId(@PathVariable Integer publicacaoId){
		return new ResponseEntity<>(comentarioService.findComentariosByPublicacaoId(publicacaoId), HttpStatus.OK);
	}

}
