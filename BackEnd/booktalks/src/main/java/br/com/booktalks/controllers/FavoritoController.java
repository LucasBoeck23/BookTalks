package br.com.booktalks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.booktalks.dto.FavoritoDto;
import br.com.booktalks.services.FavoritoService;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

	@Autowired
	FavoritoService favoritoService;
	
	@PostMapping("/favoritar/{pessoaId}/{publicacaoId}")
	public ResponseEntity<FavoritoDto> favoritar (@PathVariable Integer pessoaId , @PathVariable Integer publicacaoId){
		return new ResponseEntity<>(favoritoService.favoritar(publicacaoId, pessoaId),HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<FavoritoDto>>findAll(){
		return new ResponseEntity<>(favoritoService.findAll(),HttpStatus.OK);
	}
	@GetMapping("/pessoa/{pessoaId}")
	public ResponseEntity<List<FavoritoDto>>findAllFavoritosByPessoaId(@PathVariable Integer pessoaId){
		return new ResponseEntity<>(favoritoService.findAllFavoritosByPessoaId(pessoaId),HttpStatus.OK);
	}
}
