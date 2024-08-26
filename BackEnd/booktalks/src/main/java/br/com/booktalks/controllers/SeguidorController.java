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

import br.com.booktalks.dto.PessoaDto;
import br.com.booktalks.dto.SeguidorDto;
import br.com.booktalks.services.SeguidorService;

@RestController
@RequestMapping("/seguidor")
public class SeguidorController {

	@Autowired
	SeguidorService seguidorService;
	
	@PostMapping("/seguir/{pessoaSeguidoraId}/{pessoaId}")
	public ResponseEntity<SeguidorDto> seguir (@PathVariable Integer pessoaSeguidoraId , @PathVariable Integer pessoaId){
		return new ResponseEntity<>(seguidorService.seguir(pessoaId, pessoaSeguidoraId),HttpStatus.OK);
	}
	
	@GetMapping("seguidores/{pessoaId}")
	public ResponseEntity<List<PessoaDto>> findAllSeguidoresByPessoaId (@PathVariable Integer pessoaId){
		return new ResponseEntity<>(seguidorService.findAllSeguidoresByPessoaId(pessoaId),HttpStatus.OK);
	}
	@GetMapping("seguindo/{pessoaId}")
	public ResponseEntity<List<PessoaDto>> findAllSeguindoByPessoaId (@PathVariable Integer pessoaId){
		return new ResponseEntity<>(seguidorService.findAllSeguindoByPessoaId(pessoaId),HttpStatus.OK);
	}
}