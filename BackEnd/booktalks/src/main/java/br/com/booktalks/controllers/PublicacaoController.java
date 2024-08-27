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
	
	//-----------Controllers de like, republicado e comentarios------------//
	
	//------------LIKE----------//

	@PutMapping("/likes/{pessoaId}/{postId}")
	public ResponseEntity<LikeDto>like( @PathVariable Integer pessoaId , @PathVariable Integer postId){
		return new ResponseEntity<>(publicacaoService.like(pessoaId,postId), HttpStatus.OK);
	}
	
	//retorna todos os likes que tem do banco
	@GetMapping("/likes")
	public ResponseEntity<List<LikeDto>> findAllLike(){
		return new ResponseEntity<>(publicacaoService.findAllLike(),HttpStatus.OK);
	}
	//retorna todas as pessoas que curtiram uma publicação com o {id}
	@GetMapping("/likes/publicacao/{id}")
	public ResponseEntity<List<PessoaDto>> findAllPessoaByPublicacao(@PathVariable Integer id){
		return new ResponseEntity<>(publicacaoService.findPessoaByLikePublicacao(id),HttpStatus.OK);
	}
	//retorna todas as publicacoes curtidas de uma única pessoa
	@GetMapping("likes/pessoa/{id}")
	public ResponseEntity<List<PublicacaoDto>> findPublicacaoCurtidaByPessoa(@PathVariable Integer id){
		return new ResponseEntity<>(publicacaoService.findPublicacaoCurtidaByPessoa(id),HttpStatus.OK);
	}
	//retorna todas as publicacoes criadas por uma pessoa
	@GetMapping("/pessoa/{id}")
	public ResponseEntity<List<PublicacaoDto>> findAllPublicacaoByPessoa(@PathVariable Integer id){
		return new ResponseEntity<>(publicacaoService.findAllPublicacaoByPessoa(id),HttpStatus.OK);
	}
	

	//----------REPUBLICADOS----------//
	
	//funcao de republicar
	@PutMapping("/republicar/{pessoaId}/{postId}")
	public ResponseEntity<RepublicadoDto> republicar( @PathVariable Integer pessoaId , @PathVariable Integer postId){
		return new ResponseEntity<>(publicacaoService.republicar(pessoaId, postId), HttpStatus.OK);
	}
	
	//pega todos as republicações
	@GetMapping("/republicados")
	public ResponseEntity<List<RepublicadoDto>> findAllRepublicados(){
		return new ResponseEntity<>(publicacaoService.findAllRepublicados(), HttpStatus.OK);
	}
	//pega as pessoas que republicaram
	@GetMapping("/republicados/publicacao/{id}")
	public ResponseEntity<List<PessoaDto>> findAllPessoaByRepublicados(@PathVariable Integer id){
		return new ResponseEntity<>(publicacaoService.findAllPessoaByRepublicados(id), HttpStatus.OK);
	}
	//pega todas as republicações de uma pessoa
	@GetMapping("/republicados/pessoa/{id}")
	public ResponseEntity<List<RepublicadoDto>> findRepublicacoesByPessoaId(@PathVariable Integer id){
		return new ResponseEntity<>(publicacaoService.findRepublicacoesByPessoaId(id), HttpStatus.OK);
	}
	
	//------------------------------- FIM --------------------------------//
	
	@PutMapping
	public ResponseEntity<PublicacaoDto>update(@RequestBody Publicacao publicacao){
		return new ResponseEntity<>(publicacaoService.update(publicacao), HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PublicacaoDto>delete(@PathVariable Integer id){
		return new ResponseEntity<>(publicacaoService.delete(id), HttpStatus.OK);
	}
}
