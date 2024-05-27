package br.org.serratec.apig4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.apig4.dto.RelacionamentoDTO;
import br.org.serratec.apig4.dto.RelacionamentoInserirDTO;
import br.org.serratec.apig4.model.Relacionamento;
import br.org.serratec.apig4.service.RelacionamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/relacionamentos")
public class RelacionamentoController {

	@Autowired
	private RelacionamentoService relacionamentoService;

	@GetMapping
	@Operation(summary = "Lista todos os relacionamentos", description = "A resposta da requisição irá listar todos os relacionamentos")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<List<RelacionamentoDTO>> findAll() {
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Login do usuario: " + details.getUsername());
		return ResponseEntity.ok(relacionamentoService.findAll());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Lista um relacionamento", description = "A resposta da requisição irá listar um relacionamento específico")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<List<Relacionamento>> buscarSeguidoresByUsuarioId(@PathVariable Long id) {
		return ResponseEntity.ok(relacionamentoService.buscarSeguidoresByUsuarioId(id));
	}

	@PostMapping
	@Operation(summary = "Insere um relacionamento", description = "A requisição irá gerar um novo relacionamento")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<RelacionamentoDTO> inserir(@RequestBody RelacionamentoInserirDTO relacionamentoInserirDTO) {
		RelacionamentoDTO relacionamentoDTO = relacionamentoService.inserir(relacionamentoInserirDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(relacionamentoDTO);

	}

	@DeleteMapping("/{idUsuarioSeguidor}/{idUsuarioSeguido}")
	@Operation(summary = "Deleta um relacionamento", description = "A requisição irá apagar um relacionamento")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Relacionamento.class), mediaType = "apllication/json") }),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso") })
	public ResponseEntity<Void> deletar(@PathVariable Long idUsuarioSeguidor, @PathVariable Long idUsuarioSeguido) {
		relacionamentoService.deletar(idUsuarioSeguidor, idUsuarioSeguido);
		return ResponseEntity.noContent().build();
	}
}