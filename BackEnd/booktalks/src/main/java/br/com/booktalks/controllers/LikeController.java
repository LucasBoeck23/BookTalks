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

import br.com.booktalks.dto.LikeDto;
import br.com.booktalks.dto.PessoaDto;
import br.com.booktalks.dto.PublicacaoDto;
import br.com.booktalks.services.LikeService;

@RestController
@RequestMapping("/like")
public class LikeController {

	@Autowired
	LikeService likeService;
	
	@PutMapping("/{pessoaId}/{postId}")
	public ResponseEntity<LikeDto>like( @PathVariable Integer pessoaId , @PathVariable Integer postId){
		return new ResponseEntity<>(likeService.like(pessoaId,postId), HttpStatus.OK);
	}
	
	//retorna todos os likes que tem do banco
	@GetMapping
	public ResponseEntity<List<LikeDto>> findAllLike(){
		return new ResponseEntity<>(likeService.findAllLike(),HttpStatus.OK);
	}
	//retorna todas as pessoas que curtiram uma publicação com o {id}
	@GetMapping("/publicacao/{id}")
	public ResponseEntity<List<PessoaDto>> findAllPessoaByPublicacao(@PathVariable Integer id){
		return new ResponseEntity<>(likeService.findPessoaByLikePublicacao(id),HttpStatus.OK);
	}
	//retorna todas as publicacoes curtidas de uma única pessoa
	@GetMapping("/pessoa/{id}")
	public ResponseEntity<List<PublicacaoDto>> findPublicacaoCurtidaByPessoa(@PathVariable Integer id){
		return new ResponseEntity<>(likeService.findPublicacaoCurtidaByPessoa(id),HttpStatus.OK);
	}
	//retorna todas as publicacoes criadas por uma pessoa
	@GetMapping("/publicacoes/pessoa/{id}")
	public ResponseEntity<List<PublicacaoDto>> findAllPublicacaoByPessoa(@PathVariable Integer id){
		return new ResponseEntity<>(likeService.findAllPublicacaoByPessoa(id),HttpStatus.OK);
	}
	
}
