package br.com.booktalks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.booktalks.dto.PessoaDto;
import br.com.booktalks.dto.RepublicadoDto;
import br.com.booktalks.services.RepublicadoService;

@RestController
@RequestMapping("/republicado")
public class RepublicadoController {
	
	@Autowired
	RepublicadoService republicadoService;
	
		//funcao de republicar
		@PutMapping("/republicar/{pessoaId}/{postId}")
		public ResponseEntity<RepublicadoDto> republicar( @PathVariable Integer pessoaId , @PathVariable Integer postId){
			return new ResponseEntity<>(republicadoService.republicar(pessoaId, postId), HttpStatus.OK);
		}
		
		//pega todos as republicações
		@GetMapping
		public ResponseEntity<List<RepublicadoDto>> findAllRepublicados(){
			return new ResponseEntity<>(republicadoService.findAllRepublicados(), HttpStatus.OK);
		}
		//pega as pessoas que republicaram
		@GetMapping("/publicacao/{id}")
		public ResponseEntity<List<PessoaDto>> findAllPessoaByRepublicados(@PathVariable Integer id){
			return new ResponseEntity<>(republicadoService.findAllPessoaByRepublicados(id), HttpStatus.OK);
		}
		//pega todas as republicações de uma pessoa
		@GetMapping("/pessoa/{id}")
		public ResponseEntity<List<RepublicadoDto>> findRepublicacoesByPessoaId(@PathVariable Integer id){
			return new ResponseEntity<>(republicadoService.findRepublicacoesByPessoaId(id), HttpStatus.OK);
		}
	
}
