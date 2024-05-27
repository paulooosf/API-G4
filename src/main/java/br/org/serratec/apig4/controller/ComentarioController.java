package br.org.serratec.apig4.controller;

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

import br.org.serratec.apig4.dto.ComentarioDTO;
import br.org.serratec.apig4.model.Comentario;
import br.org.serratec.apig4.model.Relacionamento;
import br.org.serratec.apig4.service.ComentarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagem/{postagemId}/comentarios")
public class ComentarioController {

	@Autowired
	private ComentarioService comentarioService;

	@GetMapping
	@Operation(summary = "Lista todos os comentários", description = "A resposta da requisição irá listar todos os comentários")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<Page<Comentario>> listar(
			@PageableDefault(sort = "horaCriacao", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable,
			@PathVariable Long postagemId) {

		return ResponseEntity.ok(comentarioService.listar(pageable, postagemId));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Busca um comentário por ID", description = "A resposta da requisição irá exibir o comentário de ID especificado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<Comentario> buscarPorId(@PathVariable Long id, @PathVariable Long postagemId) {

		return ResponseEntity.ok(comentarioService.buscarPorId(id, postagemId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Insere um novo comentário", description = "A requisição irá postar um novo comentário")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<Comentario> inserir(@Valid @RequestBody ComentarioDTO comentarioDTO,
			@PathVariable Long postagemId, Long usuarioId) {

		comentarioService.inserir(comentarioDTO, postagemId, usuarioId);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	@Operation(summary = "Edita um comentário", description = "A requisição irá editar um comentário já existente")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<Comentario> editar(@PathVariable Long id, @RequestBody ComentarioDTO comentarioDTO,
			@PathVariable Long postagemId) {

		return ResponseEntity.ok(comentarioService.editar(id, comentarioDTO, postagemId));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Remove um comentário", description = "A requisição irá remover um comentário")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<Void> deletar(@PathVariable Long id, @PathVariable Long postagemId) {
		comentarioService.deletar(id, postagemId);

		return ResponseEntity.noContent().build();
	}
}
