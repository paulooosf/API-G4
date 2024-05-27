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

import br.org.serratec.apig4.dto.UsuarioDTO;
import br.org.serratec.apig4.model.Relacionamento;
import br.org.serratec.apig4.model.Usuario;
import br.org.serratec.apig4.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@GetMapping("/lista")
	@Operation(summary = "Lista todos os usuários", description = "A resposta da requisição irá listar todos os usuários")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<Page<UsuarioDTO>> listar(
			@PageableDefault(sort = "nome", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pegeable) {
		return ResponseEntity.ok(usuarioService.listar(pegeable));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Busca um usuário por ID", description = "A resposta da requisição irá exibir o usuário de ID especificado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<UsuarioDTO> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(usuarioService.buscar(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Insere um novo usuário", description = "A requisição irá cadastrar um novo usuário")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<UsuarioDTO> cadastrar(@Valid @RequestBody Usuario usuario) {
		UsuarioDTO usuarioDTO = usuarioService.cadastrar(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId())
				.toUri();
		return ResponseEntity.created(uri).body(usuarioDTO);
	}

	@PutMapping("/editar/{id}")
	@Operation(summary = "Edita um cadastro de usuário", description = "A requisição irá editar um usuário já existente")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<UsuarioDTO> editar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		return ResponseEntity.ok(usuarioService.editar(id, usuario));
	}

	@PutMapping("/trocarsenha/{id}")
	@Operation(summary = "Edita a senha de um usuário", description = "A requisição irá editar a senha de um usuário já existente")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<UsuarioDTO> trocarSenha(@PathVariable Long id, @Valid @RequestBody String senha) {
		return ResponseEntity.ok(usuarioService.trocarSenha(id, senha));
	}

	@DeleteMapping("/deletar/{id}")
	@Operation(summary = "Remove um usuário", description = "A requisição irá remover um cadastro de usuário")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		usuarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
