package br.org.serratec.apig4.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.apig4.model.Postagem;
import br.org.serratec.apig4.model.Relacionamento;
import br.org.serratec.apig4.service.PostagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagem")
public class PostagemController {

	@Autowired
	PostagemService postagemService;

	@GetMapping("/lista")
	@Operation(summary = "Lista todas as postagens", description = "A resposta da requisição irá listar todas as postagens")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<Page<Postagem>> listar(
			@PageableDefault(sort = "dataCriacao", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pegeable) {
		return ResponseEntity.ok(postagemService.listar(pegeable));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Busca uma postagem por ID", description = "A resposta da requisição irá exibir a postagem de ID especificado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<Postagem> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(postagemService.buscar(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Insere uma nova postagem", description = "A requisição irá realizar uma nova postagem")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<Postagem> postar(@Valid @RequestBody Postagem postagem) {
		postagemService.postar(postagem);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(postagem.getId())
				.toUri();
		return ResponseEntity.created(uri).body(postagem);
	}

	@PutMapping("/editar/{id}")
	@Operation(summary = "Edita uma postagem", description = "A requisição irá editar uma postagem já existente")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<Postagem> editar(@PathVariable Long id, @Valid @RequestBody Postagem postagem) {
		return ResponseEntity.ok(postagemService.editar(id, postagem));
	}

	@DeleteMapping("/deletar/{id}")
	@Operation(summary = "Remove uma postagem", description = "A requisição irá remover uma postagem")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		postagemService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
